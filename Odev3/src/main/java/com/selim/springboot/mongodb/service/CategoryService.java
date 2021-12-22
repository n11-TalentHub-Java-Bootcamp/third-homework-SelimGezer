package com.selim.springboot.mongodb.service;

import com.selim.springboot.mongodb.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(String id) ;

    Category save(Category category);

    void delete(String id) ;
}
