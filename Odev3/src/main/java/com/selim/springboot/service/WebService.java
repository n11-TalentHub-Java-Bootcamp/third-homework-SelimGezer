package com.selim.springboot.service;

import com.selim.springboot.converter.ResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebService {

    @Autowired
//    @Qualifier("xml")
    private ResponseConverter xmlResponseConverter;

    public void covertResponse(){
        xmlResponseConverter.convert();
    }
}
