package com.selim.springboot.entity;



import javax.persistence.*;
import java.util.Date;


@Entity
@Table(
        name = "URUN_YORUMLARI"
)
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 500, name = "YORUM")
    private String yorum;

    @Column(name = "YORUM_TARIHI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yorumTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="URUN_ID",
            foreignKey = @ForeignKey(name="FK_URUN_YORUM_ID"))
    private Urun urun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="KULLANICI_ID",
            foreignKey = @ForeignKey(name="FK_KULLANICI_YORUM_ID"))
   // @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ProductComment() {
    }

    public ProductComment(Long id, String yorum, Date yorumTarihi) {
        this.id = id;
        this.yorum = yorum;
        this.yorumTarihi = yorumTarihi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public User getKullanici() {
        return user;
    }

    public void setKullanici(User user) {
        this.user = user;
    }

   /* @Override
    public String toString() {
        return "UrunYorum{" +
                "id=" + id +
                ", yorum='" + yorum + '\'' +
                ", yorumTarihi=" + yorumTarihi +
                ", urun=" + urun +
                ", kullanici=" + kullanici +
                '}';
    }*/
}
