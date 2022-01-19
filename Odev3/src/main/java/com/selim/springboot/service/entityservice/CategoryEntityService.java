package com.selim.springboot.service.entityservice;

import com.selim.springboot.dao.CategoryDao;
import com.selim.springboot.entity.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryEntityService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Kategori> findAll(){
        return (List<Kategori>) categoryDao.findAll();
    }

    public Kategori findById(Long id){
        Optional<Kategori> optionalKategori = categoryDao.findById(id);

        Kategori kategori = null;
        if (optionalKategori.isPresent()){
            kategori = optionalKategori.get();
        }

        return kategori;
    }

    public Kategori save(Kategori kategori){
        return categoryDao.save(kategori);
    }

    public void delete(Kategori kategori){
        categoryDao.delete(kategori);
    }

    public void deleteById(Long id){
        categoryDao.deleteById(id);
    }

    public long count(){
        return categoryDao.count();
    }

    public List<Kategori> findAllByUstKategoriIsNull(){
        return categoryDao.findAllByUstKategoriIsNullOrderByAdiDesc();
    }

    public List<Kategori> findAllByAdiEndsWith(String adi){
        return categoryDao.findAllByAdiEndsWith(adi);
    }
}