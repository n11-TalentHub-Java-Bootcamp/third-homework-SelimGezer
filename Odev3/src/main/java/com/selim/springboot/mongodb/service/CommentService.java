package com.selim.springboot.mongodb.service;

import com.selim.springboot.mongodb.entity.Category;
import com.selim.springboot.mongodb.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(String id) ;

    Comment save(Comment comment);

    void delete(String id) ;
}
