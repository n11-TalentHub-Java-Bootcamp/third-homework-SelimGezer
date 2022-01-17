package com.selim.springboot.converter;

import com.selim.springboot.dto.ProductDetailDto;
import com.selim.springboot.dto.ProductDto;
import com.selim.springboot.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrunConverter {

    UrunConverter INSTANCE = Mappers.getMapper(UrunConverter.class);

    @Mapping(source = "kategoriId", target = "kategori.id")
    Urun convertUrunDtoToUrun(ProductDto productDto);

    @Mapping(target = "kategoriId", source = "kategori.id")
    ProductDto convertUrunToUrunDto(Urun urun);

    @Mapping(source = "fiyat", target = "urunFiyati")
    @Mapping(source = "adi", target = "urunAdi")
    @Mapping(source = "kategori.adi", target = "kategoriAdi")
    ProductDetailDto convertUrunToUrunDetayDto(Urun urun);

    @Mapping(source = "fiyat", target = "urunFiyati")
    @Mapping(source = "adi", target = "urunAdi")
    @Mapping(source = "kategori.adi", target = "kategoriAdi")
    List<ProductDetailDto> convertAllUrunListToUrunDetayDtoList(List<Urun> urunList);
}
