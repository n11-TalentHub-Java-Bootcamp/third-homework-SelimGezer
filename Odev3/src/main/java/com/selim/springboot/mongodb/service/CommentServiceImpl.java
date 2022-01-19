package com.selim.springboot.mongodb.service;

import com.selim.springboot.mongodb.entity.Comment;
import com.selim.springboot.mongodb.service.entityservice.CommentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentEntityService commentEntityService;

    @Override
    public List<Comment> findAll() {
        return commentEntityService.findAll();
    }

    @Override
    public Comment findById(String id) {
        return commentEntityService.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentEntityService.save(comment);
    }

    @Override
    public void delete(String id) {
        commentEntityService.delete(id);
    }
}
