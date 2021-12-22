package com.selim.springboot.converter;

import com.selim.springboot.dto.CommentInsertDto;
import com.selim.springboot.entity.ProductComment;

public class CommentMapper {

    public static CommentInsertDto toDto(ProductComment productComment){
        CommentInsertDto commentInsertDto=new CommentInsertDto(productComment.getYorum(), productComment.getYorumTarihi(), productComment.getKullanici().getId(), productComment.getUrun().getId());
        return commentInsertDto;
    }
    public static ProductComment toEntity(CommentInsertDto commentInsertDto){
        ProductComment productComment =new ProductComment(-1L,commentInsertDto.getYorum(),commentInsertDto.getYorumTarihi());
        return productComment;
    }
}
