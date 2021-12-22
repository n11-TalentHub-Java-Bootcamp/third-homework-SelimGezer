package com.selim.springboot.dao;

import com.selim.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

 //   @Query("select ku from Kullanıcı ku where ku.kullaniciAdi=:kullaniciAdi")
   // public Kullanıcı getKullaniciByKullaniciAdi(String kullaniciAdi);
    public User getByKullaniciAdi(String kullaniciAdi);

   // @Query(value = "select ku from Kullanıcı ku where ku.telefon=:kullaniciTel ")
   // public Kullanıcı getKullaniciByKullaniciTel(String kullaniciTel);
    public User getByTelefon(String kullaniciTel);

    @Modifying(clearAutomatically = true)
    @Query(value = "update User ku set ku.kullaniciAdi=:kullaniciAdi where ku.id=:id")
    public void updateKullaniciAdiByKullaniciID(@Param("id") Long id,@Param("kullaniciAdi") String kullaniciAdi);


}
