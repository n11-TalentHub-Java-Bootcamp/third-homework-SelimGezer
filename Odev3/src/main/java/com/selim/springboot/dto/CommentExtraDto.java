package com.selim.springboot.dto;

import java.util.Date;

public class CommentExtraDto extends CommentDto{

    private long urunId;
    private long kullaniciId;


    public CommentExtraDto(String yorum, Date yorumTarihi, long urunId, long kullaniciId) {
        super(yorum, yorumTarihi);
        this.urunId = urunId;
        this.kullaniciId = kullaniciId;
    }

    public long getUrunId() {
        return urunId;
    }

    public void setUrunId(long urunId) {
        this.urunId = urunId;
    }

    public long getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(long kullaniciId) {
        this.kullaniciId = kullaniciId;
    }
}
