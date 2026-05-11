package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.dto.CategoriaDTO;
import com.digital_arcade_spring.digital_arcade.model.Categoria;
import com.digital_arcade_spring.digital_arcade.model.Categorias;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.repository.CategoriaRepository;
import com.digital_arcade_spring.digital_arcade.repository.CategoriasRepository;
import com.digital_arcade_spring.digital_arcade.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriasRepository categoriasPuenteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Categoria crearCategoria(CategoriaDTO dto) {
        log.info("Registrando nueva categoría para la tienda de comunidad: {}", dto.getNombre());
        Categoria cat = new Categoria();
        cat.setNombre(dto.getNombre());
        cat.setDescripcion(dto.getDescripcion());
        return categoriaRepository.save(cat);
    }

    @Transactional
    public Categorias enlazarItemACategoria(Integer itemId, Integer categoriaId) {
        log.info("Enlazando en puente 'categorias': Item ID {} con Categoria ID {}", itemId, categoriaId);
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Ítem de comunidad no encontrado"));
        Categoria cat = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Categorias puente = new Categorias();
        puente.setItem(item);
        puente.setCategoria(cat);
        return categoriasPuenteRepository.save(puente);
    }
}
