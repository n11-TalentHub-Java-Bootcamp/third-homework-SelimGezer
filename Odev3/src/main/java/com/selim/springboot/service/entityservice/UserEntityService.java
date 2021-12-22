package com.selim.springboot.service.entityservice;

import com.selim.springboot.converter.UserMapper;
import com.selim.springboot.dao.UserDao;
import com.selim.springboot.dto.UserDto;
import com.selim.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }

    public User getByKullaniciAdi(String kullaniciAdi){
        return userDao.getByKullaniciAdi(kullaniciAdi);
    }

    public User getByTelefon(String kullaniciTel){
        return  userDao.getByTelefon(kullaniciTel);
    }

    public void deleteById(Long id){
        userDao.deleteById(id);
    }

    public void insertUser(UserDto userDto){
        userDao.save(UserMapper.toEntity(-1L,userDto));//Todo:-1 olarak gönderilip id seçimi veritabanına bırakılmıştır.
    }


    public ResponseEntity<String> updateKullaniciByID(Long id, UserDto kullanıcı){//Todo: Sistemde kullanıcının varlığı sorgulanıp sonrasında güncelleme işlemine tabi tutuluyor.
        Optional<User> optionalKullanici= userDao.findById(id);
        if(optionalKullanici.isPresent()){
            UserMapper userMapper =new UserMapper();
            User guncelUser = userMapper.toEntity(id,kullanıcı);
            userDao.save(guncelUser);
            return new ResponseEntity<String>("Kullanıcı Güncellendi!",HttpStatus.OK);
        }else{
            System.out.println("Belirtilen id de bir kullanıcı bulunmuyor!");
            return new ResponseEntity<String>("Kullanıcı bulunmuyor!",HttpStatus.NOT_FOUND);
        }
    }



}
