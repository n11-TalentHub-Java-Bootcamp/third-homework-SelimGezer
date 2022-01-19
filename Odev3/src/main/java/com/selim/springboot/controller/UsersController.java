package com.selim.springboot.controller;

import com.selim.springboot.dto.UserDto;
import com.selim.springboot.entity.User;
import com.selim.springboot.service.entityservice.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userEntityService.findAll();
    }

    @GetMapping(value = "/names",params = {"username"})
    public User getByUsername(
            @RequestParam(value ="username") String username){
        return userEntityService.getByKullaniciAdi(username);
    }

    @GetMapping(value = "/phones",params = {"phoneNumber"})
    public User getByPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber){
        return userEntityService.getByTelefon(phoneNumber);
    }

    @PostMapping("")
    public void addUser(@RequestBody UserDto userDto){
        userEntityService.insertUser(userDto);
    }

    @DeleteMapping(value = "",params = {"username","phoneNumber"})
    public ResponseEntity<String> deleteById(@RequestParam(value="username") String username, @RequestParam(value = "phoneNumber") String phoneNumber ){

        User kullaniciByUserAdi = userEntityService.getByKullaniciAdi(username);
        String mesaj=null;
        if(kullaniciByUserAdi ==null){
            System.out.println("Belirtilen kullaniciAdi ile ilgili kullanıcı bulunamadı!");
            mesaj=String.format("%s kullanıcı adına sahip bir kullanıcı bulunamadı!",username);
            return new ResponseEntity(mesaj,HttpStatus.OK);
        }else{
            if(kullaniciByUserAdi.getTelefon().equals(phoneNumber)){
                userEntityService.deleteById(kullaniciByUserAdi.getId());
                return new ResponseEntity("Kullanıcı başarıyla silindi.",HttpStatus.OK);
            }else{
                mesaj=String.format("Kullanıcı Adı: %s ile belirtilen %s numaralı telefon numarası eşleşmiyor!",username,phoneNumber);
                return new ResponseEntity(mesaj,HttpStatus.OK);
            }
        }
    }

   @PutMapping(value = "/updateUser",params = {"id"})
    public ResponseEntity updateKullanici(@RequestParam(value="id") Long id,@RequestBody UserDto userDto){
       return userEntityService.updateKullaniciByID(id, userDto);
    }

}
