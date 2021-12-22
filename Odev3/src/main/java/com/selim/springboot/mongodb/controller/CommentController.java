package com.selim.springboot.mongodb.controller;


import com.selim.springboot.mongodb.entity.Category;
import com.selim.springboot.mongodb.entity.Comment;
import com.selim.springboot.mongodb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/commentsMongo")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable String id) {
        return commentService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody Comment comment) {

        comment = commentService.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        commentService.delete(id);
    }



}
