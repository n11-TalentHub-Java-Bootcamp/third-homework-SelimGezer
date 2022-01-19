package com.selim.springboot.converter;

import com.selim.springboot.dto.CategoryDto;
import com.selim.springboot.entity.Kategori;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KategoriConverter {

    KategoriConverter INSTANCE = Mappers.getMapper(KategoriConverter.class);

    @Mapping(target = "ustKategoriId", source = "ustKategori.id")
    CategoryDto convertKategoriToKategoriDto(Kategori kategori);

    @Mapping(target = "ustKategoriId", source = "ustKategori.id")
    List<CategoryDto> convertAllKategoriListToKategoriDtoList(List<Kategori> kategoriList);

//    @Mapping(target = "ustKategori.id", source = "ustKategoriId", expression = "java(null))
//    @Mapping(target = "ustKategori.id", source = "ustKategoriId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)

//    @Mapping(target = "ustKategori.id", expression = "java(kategoriDto.getUstKategoriId() == null ? null : " +
//            "kategoriDto.getUstKategoriId())")
    Kategori convertKategoriDtoToKategori(CategoryDto categoryDto);
}
