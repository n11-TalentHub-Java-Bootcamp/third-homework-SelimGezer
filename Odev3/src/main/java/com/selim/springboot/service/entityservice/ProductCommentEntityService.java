package com.selim.springboot.service.entityservice;

import com.selim.springboot.converter.CommentMapper;
import com.selim.springboot.core.Result;
import com.selim.springboot.core.ResultWithData;
import com.selim.springboot.dao.ProductCommentDao;
import com.selim.springboot.dao.ProductDao;
import com.selim.springboot.dao.UserDao;
import com.selim.springboot.dto.CommentDto;
import com.selim.springboot.dto.CommentExtraDto;
import com.selim.springboot.dto.CommentInsertDto;
import com.selim.springboot.entity.Urun;
import com.selim.springboot.entity.ProductComment;
import com.selim.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCommentEntityService {


    private ProductCommentDao productCommentDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ProductCommentEntityService(ProductCommentDao productCommentDao, UserDao userDao, ProductDao productDao) {
        this.productCommentDao = productCommentDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }


    public List<CommentExtraDto> getAll(){
        List<ProductComment> all = this.productCommentDao.findAll();
        List<CommentExtraDto> commentExtraDtos=new ArrayList<>();
        for (ProductComment productComment:all) {
            CommentExtraDto commentDto=new CommentExtraDto(productComment.getYorum(),productComment.getYorumTarihi(),productComment.getUrun().getId(),productComment.getUrun().getId());
            commentExtraDtos.add(commentDto);
        }
        return commentExtraDtos;
    }

    public Result getByUser_Id(long id){
        Optional<User> optionalUser =  userDao.findById(id);//TODO:Kullanıcın sistemde olup olmadığı kontrol ediliyor. Sistemde bulunursa yaptığı yorumlar aranıyor.
        if(optionalUser.isPresent()){
            if(this.productCommentDao.getByUser_Id(id).size()>0){
                return new ResultWithData<List<CommentDto>>("Başarılı",productCommentDao.getByUser_Id(id));
            }else{
                return new Result(String.format("%s %s adlı kullanıcın hiç yorumu bulunmamaktadır!", optionalUser.get().getAdi(), optionalUser.get().getSoyadi()));
            }
        }else{
                return new Result(String.format("%d id'e sahip kullanıcı bulunmamaktadır!",id));
        }
    }

    public Result getByUrun_Id(long id){
        Optional<Urun> optionalUrun =  productDao.findById(id); //TODO:Ürünün sitemde olup olmadığı kontrol ediliyor. VAR İSE devam edilip ürüne yapılan yorumlar taranıyor.

        if(optionalUrun.isPresent()){
            if(this.productCommentDao.getByUrun_Id(id).size()>0)
            {
                return new ResultWithData<List<CommentDto>>(productCommentDao.getByUrun_Id(id));
            }
            else{
                return new Result(String.format("Bu ürüne hiç yorum yapılmamıştır!"));
            }
        }else{
            return new Result(String.format("%d id'e sahip bir ürün bulunmamaktadır!",id));
        }
    }

    public Result deleteByComment_Id(long id){//TODO: Ürünün olup olmadığı test edilip sonrasında silme işlemi gerçekleştiriliyor.
        Optional<ProductComment> optionalUrunYorum =  productCommentDao.findById(id);
        if(optionalUrunYorum.isPresent()){
            productCommentDao.deleteById(id);
            return new Result("Silme işlemi başarıyla gerçekleştirildi.");
        }else{
            return new Result(String.format("%d id'e sahip bir ürün bulunmamaktadır!",id));
        }
    }

    public Result insertComment(CommentInsertDto commentInsertDto){//Todo: Hem kullanıcı hem de urun kontrol ediliyor. Sonrasında ekleme işlemi gerçekleştiriliyor.
        ProductComment productComment = CommentMapper.toEntity(commentInsertDto);//Todo: Dto to Entity
        Optional<User> optionalUser=userDao.findById(commentInsertDto.getUser_id());
        Optional<Urun> optionalUrun= productDao.findById(commentInsertDto.getUrun_id());

        if(!optionalUser.isPresent()){
            return new Result(String.format("%d id'e sahip bir kullanıcı bulunmuyor. Yorum eklenemez!",commentInsertDto.getUser_id()));
        }
        if(!optionalUrun.isPresent()){
            return new Result(String.format("%d id'e sahip bir urun bulunmuyor. Yorum eklenemez!",commentInsertDto.getUrun_id()));
        }

        productComment.setUrun(optionalUrun.get());
        productComment.setKullanici(optionalUser.get());
        productComment.setId(-100L);
        System.out.println("selam:"+ productComment.getId());
        productCommentDao.save(productComment);
        return new Result("Yorum başarı ile eklendi!");
    }

}
