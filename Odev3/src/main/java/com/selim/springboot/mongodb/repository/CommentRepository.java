package com.selim.springboot.mongodb.repository;

import com.selim.springboot.mongodb.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
}
