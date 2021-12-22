package com.selim.springboot.dto;

import java.util.Date;

public class CommentInsertDto extends CommentDto {

    private long urun_id;
    private long user_id;


    public CommentInsertDto(String yorum, Date yorumTarihi, long user_id, long urun_id) {
        super(yorum, yorumTarihi);
        this.urun_id = urun_id;
        this.user_id = user_id;
    }

    public long getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(long urun_id) {
        this.urun_id = urun_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
