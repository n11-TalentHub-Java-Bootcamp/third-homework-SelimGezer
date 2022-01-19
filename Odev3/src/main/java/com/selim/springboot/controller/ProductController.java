package com.selim.springboot.controller;

import com.selim.springboot.converter.UrunConverter;
import com.selim.springboot.dto.ProductDetailDto;
import com.selim.springboot.dto.ProductDto;
import com.selim.springboot.entity.Urun;
import com.selim.springboot.dto.exception.ProductNotFoundException;
import com.selim.springboot.service.entityservice.CategoryEntityService;
import com.selim.springboot.service.entityservice.ProductEntityService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductEntityService productEntityService;
    
    @Autowired
    private CategoryEntityService categoryEntityService;

    @GetMapping("")
    public MappingJacksonValue findAllProductList() {

        List<Urun> urunList = productEntityService.findAll();

        String filterName = "UrunFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(urunList);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findProductById(@PathVariable Long id){

        Urun urun = productEntityService.findById(id);

        if (urun == null){
            throw new ProductNotFoundException("Urun not found. id: " + id);
        }

        WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllProductList()
        );

        ProductDto productDto = UrunConverter.INSTANCE.convertUrunToUrunDto(urun);

        String filterName = "UrunDtoFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        EntityModel entityModel = EntityModel.of(productDto);

        entityModel.add(linkToUrun.withRel("tum-urunler"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/detail/{id}")
    public ProductDetailDto findProductDtoById(@PathVariable Long id){

        Urun urun = productEntityService.findById(id);

        if (urun == null){
            throw new ProductNotFoundException("Urun not found. id: " + id);
        }

        ProductDetailDto productDetailDto = UrunConverter.INSTANCE.convertUrunToUrunDetayDto(urun);

        return productDetailDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto){

        Urun urun = UrunConverter.INSTANCE.convertUrunDtoToUrun(productDto);

        urun = productEntityService.save(urun);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(urun.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){

        productEntityService.deleteById(id);
    }

    @GetMapping("kategoriler/{kategoriId}")
    public List<ProductDetailDto> findAllProductByCategoryId(@PathVariable Long kategoriId){

        List<Urun> urunList = productEntityService.findAllByKategoriOrderByIdDesc(kategoriId);

        List<ProductDetailDto> productDetailDtoList = UrunConverter.INSTANCE.convertAllUrunListToUrunDetayDtoList(urunList);

        return productDetailDtoList;
    }

    private SimpleFilterProvider getProductFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getProductFilter();

        return new SimpleFilterProvider().addFilter(filterName, filter);
    }

    private SimpleBeanPropertyFilter getProductFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "adi", "fiyat", "kayitTarihi");
    }
}
