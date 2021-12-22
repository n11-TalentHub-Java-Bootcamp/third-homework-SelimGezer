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

    @GetMapping(value = "/getByUsername")
    public User getByKullaniciAdi(
            @RequestParam(value ="kullaniciAdi") String kullaniciAdi){
        return userEntityService.getByKullaniciAdi(kullaniciAdi);
    }

    @GetMapping(value = "/getByPhone",params = {"tel"})
    public User getByTelefon(@RequestParam(value = "tel") String kullaniciTel){
        return userEntityService.getByTelefon(kullaniciTel);
    }

    @PostMapping("/insertUser")
    public void insertUser(@RequestBody UserDto userDto){
        userEntityService.insertUser(userDto);
    }

    @DeleteMapping(value = "/deleteUserById",params = {"kullaniciAdi","telefon"})
    public ResponseEntity<String> deleteById(@RequestParam(value="kullaniciAdi") String kullaniciAdi, @RequestParam(value = "telefon") String kullaniciTel ){

        User kullaniciByUserAdi = userEntityService.getByKullaniciAdi(kullaniciAdi);
        String mesaj=null;
        if(kullaniciByUserAdi ==null){
            System.out.println("Belirtilen kullaniciAdi ile ilgili kullanıcı bulunamadı!");
            mesaj=String.format("%s kullanıcı adına sahip bir kullanıcı bulunamadı!",kullaniciAdi);
            return new ResponseEntity(mesaj,HttpStatus.OK);
        }else{
            if(kullaniciByUserAdi.getTelefon().equals(kullaniciTel)){
                userEntityService.deleteById(kullaniciByUserAdi.getId());
                return new ResponseEntity("Kullanıcı başarıyla silindi.",HttpStatus.OK);
            }else{
                mesaj=String.format("Kullanıcı Adı: %s ile belirtilen %s numaralı telefon numarası eşleşmiyor!",kullaniciAdi,kullaniciTel);
                return new ResponseEntity(mesaj,HttpStatus.OK);
            }
        }
    }

   @PutMapping(value = "/updateUser",params = {"id"})
    public ResponseEntity updateKullanici(@RequestParam(value="id") Long id,@RequestBody UserDto userDto){
       return userEntityService.updateKullaniciByID(id, userDto);
    }

}
