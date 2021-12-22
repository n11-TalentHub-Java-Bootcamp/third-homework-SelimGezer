package com.selim.springboot.entity;

import javax.persistence.*;

@Entity
@Table(
        name = "KULLANICI"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, name = "ADI")
    private String adi;

    @Column(length = 50, name = "SOYADI")
    private String soyadi;

    @Column(length = 50, name = "EMAIL")
    private String email;

    @Column(length = 15, name = "TELEFON")
    private String telefon;

    @Column(length = 20, name = "KULLANICI_ADI")
    private String kullaniciAdi;

    public User() {
    }

    public User(Long id, String adi, String soyadi, String email, String telefon, String kullaniciAdi) {
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.email = email;
        this.telefon = telefon;
        this.kullaniciAdi = kullaniciAdi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }


    @Override
    public String toString() {
        return "Kullanıcı{" +
                "id=" + id +
                ", adi='" + adi + '\'' +
                ", soyadi='" + soyadi + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                '}';
    }
}
