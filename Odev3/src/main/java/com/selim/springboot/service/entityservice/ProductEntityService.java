package com.selim.springboot.service.entityservice;

import com.selim.springboot.dao.ProductDao;
import com.selim.springboot.entity.Urun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {

    @Autowired
    private ProductDao productDao;

    public List<Urun> findAll(){
        return (List<Urun>) productDao.findAll();
    }

    public Urun findById(Long id){
        Optional<Urun> optionalUrun = productDao.findById(id);

        Urun urun = null;
        if (optionalUrun.isPresent()){
            urun = optionalUrun.get();
        }

        return urun;
    }

    public Urun save(Urun urun){
        urun = productDao.save(urun);

        return urun;
    }

    public void delete(Urun urun){
        productDao.delete(urun);
    }

    public void deleteById(Long id){
        productDao.deleteById(id);
    }

    public long count(){
        return productDao.count();
    }

    public List<Urun> findAllByKategoriOrderByIdDesc(Long kategoriId){
        return productDao.findAllByKategoriOrderByIdDesc(kategoriId);
    }
}