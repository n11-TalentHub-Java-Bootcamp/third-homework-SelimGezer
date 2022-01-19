package com.selim.springboot.dao;

import com.selim.springboot.dto.CommentDto;
import com.selim.springboot.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentDao extends JpaRepository<ProductComment,Long> {

    List<CommentDto>  getByUser_Id(long id);

    List<CommentDto> getByUrun_Id(long id);

}
