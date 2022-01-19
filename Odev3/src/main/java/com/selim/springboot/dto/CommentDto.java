package com.selim.springboot.dto;

import java.util.Date;

public class CommentDto {
    private String yorum;
    private Date yorumTarihi;

    public CommentDto(String yorum, Date yorumTarihi) {
        this.yorum = yorum;
        this.yorumTarihi = yorumTarihi;
    }
    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public Date getYorumTarihi() {
        return yorumTarihi;
    }

    public void setYorumTarihi(Date yorumTarihi) {
        this.yorumTarihi = yorumTarihi;
    }
}
