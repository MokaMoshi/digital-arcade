package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.DTO.ItemDTO;
import com.digital_arcade_spring.digital_arcade.model.Categoria;
import com.digital_arcade_spring.digital_arcade.model.Categorias;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.repository.CategoriaRepository;
import com.digital_arcade_spring.digital_arcade.repository.CategoriasRepository; // Repositorio del puente
import com.digital_arcade_spring.digital_arcade.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
@slf4j
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriasRepository categoriasPuenteRepository;

    @Transactional
    public Item crearItemComunidad(ItemDTO dto) {
        log.info("Registrando nuevo ítem en la tienda de la comunidad: {}", dto.getNombre());

        try {
       
            Item item = new Item();
            item.setNombre(dto.getNombre());
            item.setPrecio(dto.getPrecio());
            item.setStock(dto.getStock());
            
       
            item.setImagenes(new ArrayList<>());
            item.setCategoriasPuente(new ArrayList<>());
            item.setItemsPuente(new ArrayList<>());

            Item itemGuardado = itemRepository.save(item);
            log.info("Ítem registrado exitosamente en BD con ID: {}", itemGuardado.getId());

            
            if (dto.getCategoriaIds() != null && !dto.getCategoriaIds().isEmpty()) {
                for (Integer catId : dto.getCategoriaIds()) {
                    Categoria cat = categoriaRepository.findById(catId)
                            .orElseThrow(() -> new RuntimeException("Error de integridad: Categoría no existe con ID " + catId));

                    Categorias puente = new Categorias();
                    puente.setItem(itemGuardado);
                    puente.setCategoria(cat);

                    categoriasPuenteRepository.save(puente);
                    log.info("Puente 'categorias' enlazado: Item ID {} <---> Categoria ID {}", itemGuardado.getId(), catId);
                }
            }

            return itemGuardado;

        } catch (Exception e) {
            log.error("Error transaccional al registrar el ítem {}: {}", dto.getNombre(), e.getMessage(), e);
            throw e;
        }
    }
}