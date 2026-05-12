package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.DTO.ItemCompraDTO;
import com.digital_arcade_spring.digital_arcade.DTO.TransaccionDTO;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.model.Items;
import com.digital_arcade_spring.digital_arcade.model.MetodoPago;
import com.digital_arcade_spring.digital_arcade.model.Transaccion;
import com.digital_arcade_spring.digital_arcade.model.Usuario;
import com.digital_arcade_spring.digital_arcade.repository.ItemRepository;
import com.digital_arcade_spring.digital_arcade.repository.ItemsRepository; 
import com.digital_arcade_spring.digital_arcade.repository.MetodoPagoRepository;
import com.digital_arcade_spring.digital_arcade.repository.TransaccionRepository;
import com.digital_arcade_spring.digital_arcade.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ItemsRepository itemsPuenteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${usuarios.api.url:http://localhost:8081/api/usuarios/existe/}")
    private String urlApiUsuarios;

    @Value("${usuarios.api.timeout-segundos:5}")
    private int timeoutApiUsuariosSegundos;

    @Transactional
    public Transaccion procesarTransaccion(TransaccionDTO dto) {
        log.info("Iniciando transacción en tienda comunitaria para Usuario ID: {}", dto.getUsuarioId());

        try {
            String url = urlApiUsuarios + dto.getUsuarioId();
            Boolean usuarioValido = webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .timeout(Duration.ofSeconds(timeoutApiUsuariosSegundos))
                    .onErrorReturn(false)
                    .block();

            if (Boolean.FALSE.equals(usuarioValido)) {
                log.warn("Fallo de validación remota: El miembro de la comunidad ID {} no existe o la API no responde", dto.getUsuarioId());
                throw new RuntimeException("El usuario no existe en el sistema central de la comunidad");
            }

            Usuario usuarioLocal = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario comunitario no sincronizado en BD local"));

   
            MetodoPago metodo = metodoPagoRepository.findById(dto.getMetodoPagoId())
                    .orElseThrow(() -> new RuntimeException("Método de pago inexistente"));

            if (!metodo.isActivo()) throw new RuntimeException("El método de pago no está activo actualmente");

            
            Transaccion transaccion = new Transaccion();
            transaccion.setUsuario(usuarioLocal);
            transaccion.setMetodoPago(metodo);
            transaccion.setTotal(dto.getTotal());
            transaccion.setFechaTransaccion(LocalDateTime.now());

            Transaccion guardada = transaccionRepository.save(transaccion);

            for (ItemCompraDTO detalle : dto.getItems()) {
                Item itemDb = itemRepository.findById(detalle.getItemId())
                        .orElseThrow(() -> new RuntimeException("El ítem ID " + detalle.getItemId() + " no existe en la tienda"));

                if (itemDb.getStock() < detalle.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el ítem comunitario: " + itemDb.getNombre());
                }

                itemDb.setStock(itemDb.getStock() - detalle.getCantidad());
                itemRepository.save(itemDb);

                Items puente = new Items();
                puente.setCantidad(detalle.getCantidad());
                puente.setUsuario(usuarioLocal);
                puente.setTransaccion(guardada);
                puente.setItem(itemDb);

                itemsPuenteRepository.save(puente);
                log.info("Puente 'items' registrado: Transaccion {} | Miembro {} | Item {}", guardada.getId(), usuarioLocal.getId(), itemDb.getId());
            }

            return guardada;

        } catch (Exception e) {
            log.error("Error procesando la transacción en la tienda de comunidad: {}", e.getMessage(), e);
            throw e;
        }
    }
}
