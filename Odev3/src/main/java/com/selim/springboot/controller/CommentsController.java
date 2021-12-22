package com.selim.springboot.controller;

import com.selim.springboot.core.Result;
import com.selim.springboot.dto.CommentDto;
import com.selim.springboot.dto.CommentExtraDto;
import com.selim.springboot.dto.CommentInsertDto;
import com.selim.springboot.entity.ProductComment;
import com.selim.springboot.service.entityservice.ProductCommentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productComments")
public class CommentsController {

    @Autowired
    private ProductCommentEntityService productCommentEntityService;


    @GetMapping("/getByUserId")
    private Result getByUser_Id(@RequestParam long userId){
        return this.productCommentEntityService.getByUser_Id(userId);
    }

    @GetMapping("/getAll")
    private List<CommentExtraDto> getAll(){
        return this.productCommentEntityService.getAll();
    }

    @GetMapping("/getByProductId")
    private Result getByUrun_Id(@RequestParam long productId){
        return this.productCommentEntityService.getByUrun_Id(productId);
    }

    @DeleteMapping("/deleteCommentById")
    private Result deleteByComment_Id(@RequestParam long commentId){
        return  this.productCommentEntityService.deleteByComment_Id(commentId);
    }

    @PostMapping("/insertComment")
    private Result insertComment(@RequestBody CommentInsertDto commentInsertDto){
        return this.productCommentEntityService.insertComment(commentInsertDto);
    }

}
