package com.selim.springboot.mongodb.service.entityservice;


import com.selim.springboot.mongodb.entity.Category;
import com.selim.springboot.mongodb.entity.Comment;
import com.selim.springboot.mongodb.repository.CategoryRepository;
import com.selim.springboot.mongodb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentEntityService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll(){
        return  commentRepository.findAll();
    }

    public Comment findById(String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        Comment comment = null;
        if (optionalComment.isPresent()){
            comment = optionalComment.get();
        }

        return comment;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(String id) {
        commentRepository.deleteById(id);
    }

}
