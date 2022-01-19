package com.selim.springboot.converter;

import com.selim.springboot.dto.UserDto;
import com.selim.springboot.entity.User;

public class UserMapper {

    public static UserDto toDto(User entity){
        UserDto userDto =new UserDto(entity.getAdi(),entity.getSoyadi(),entity.getEmail(),entity.getTelefon(),entity.getKullaniciAdi());
        return userDto;
    }

    public static User toEntity(Long id, UserDto dto){
        User entity=new User(id,dto.getAdi(),dto.getSoyadi(),dto.getEmail(),dto.getTelefon(),dto.getKullaniciAdi());
        return entity;
    }

}
