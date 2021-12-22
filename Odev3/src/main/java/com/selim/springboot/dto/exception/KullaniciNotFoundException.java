package com.selim.springboot.dto.exception;


public class KullaniciNotFoundException extends NotFound {

    private String kullaniciAdi;
    private String kullaniciTel;

    public KullaniciNotFoundException(String mesaj, String kullaniciAdi, String kullaniciTel) {
        super(mesaj);
        this.kullaniciAdi = kullaniciAdi;
        this.kullaniciTel = kullaniciTel;
    }

    public KullaniciNotFoundException(String message,String kullaniciAdi) {
        super(message);
        this.kullaniciAdi=kullaniciAdi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getKullaniciTel() {
        return kullaniciTel;
    }

}
