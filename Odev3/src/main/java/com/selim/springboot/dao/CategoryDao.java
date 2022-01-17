package com.selim.springboot.dao;

import com.selim.springboot.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Kategori, Long> {

    List<Kategori> findAllByUstKategoriIsNullOrderByAdiDesc();

    List<Kategori> findAllByAdiEndsWith(String adi);

}