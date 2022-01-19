package com.selim.springboot.controller;

import com.selim.springboot.converter.KategoriConverter;
import com.selim.springboot.converter.UrunConverter;
import com.selim.springboot.dto.CategoryDto;
import com.selim.springboot.dto.ProductDetailDto;
import com.selim.springboot.entity.Kategori;
import com.selim.springboot.entity.Urun;
import com.selim.springboot.service.entityservice.CategoryEntityService;
import com.selim.springboot.service.entityservice.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private ProductEntityService productEntityService;

    @GetMapping("")
    public List<CategoryDto> findAll(){

        List<Kategori> kategoriList = categoryEntityService.findAll();

        List<CategoryDto> categoryDtoList = KategoriConverter.INSTANCE.convertAllKategoriListToKategoriDtoList(kategoriList);

        return categoryDtoList;
    }

    @GetMapping("/{id}")
    public Kategori findById(@PathVariable Long id){

        Kategori kategori = categoryEntityService.findById(id);

        return kategori;
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody CategoryDto categoryDto){ //TODO: Input değeri dto tipinde olmalı

        Kategori kategori = KategoriConverter.INSTANCE.convertKategoriDtoToKategori(categoryDto);

        //TODO: Check it
        if (kategori.getUstKategori() != null && kategori.getUstKategori().getId() == null){
            kategori.setUstKategori(null);
        }

        kategori = categoryEntityService.save(kategori);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(kategori.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("")
    public CategoryDto update(@RequestBody CategoryDto categoryDto){//TODO: Input değeri dto tipinde olmalı

        Kategori kategori = KategoriConverter.INSTANCE.convertKategoriDtoToKategori(categoryDto);

        //TODO: Check it
        if (kategori.getUstKategori() != null && kategori.getUstKategori().getId() == null){
            kategori.setUstKategori(null);
        }

        kategori = categoryEntityService.save(kategori);

        CategoryDto categoryDtoResult = KategoriConverter.INSTANCE.convertKategoriToKategoriDto(kategori);

        return categoryDtoResult;
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        categoryEntityService.deleteById(id);
    }

    // localhost:8080/api/kategoriler/{id}/urunler
    @GetMapping("/{id}/urunler")
    public List<ProductDetailDto> findAllUrunByKategoriId(@PathVariable Long id){
        List<Urun> urunList = productEntityService.findAllByKategoriOrderByIdDesc(id);

        List<ProductDetailDto> productDetailDtoList = UrunConverter.INSTANCE.convertAllUrunListToUrunDetayDtoList(urunList);

        return productDetailDtoList;
    }
}
