package com.selim.springboot;

import com.selim.springboot.entity.Kategori;
import com.selim.springboot.entity.Urun;
import com.selim.springboot.service.entityservice.CategoryEntityService;
import com.selim.springboot.service.entityservice.ProductEntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootTrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

		CategoryEntityService categoryEntityService = applicationContext.getBean(CategoryEntityService.class);
		ProductEntityService productEntityService = applicationContext.getBean(ProductEntityService.class);

//		Kategori kategori = getTelefonKategori(service);

//		getSamsungM31(kategoriEntityService, urunEntityService);

//		deleteUrunList(urunEntityService);

//		findAllUrunList(urunEntityService);

//		saveKitap(kategoriEntityService);

//		findAllUrunList(kategoriEntityService);
	}

	private static void saveKitap(CategoryEntityService categoryEntityService) {
		Kategori kitap = new Kategori();
		kitap.setAdi("Kitap");
		kitap.setKirilim(1L);

		kitap = categoryEntityService.save(kitap);

		Kategori kitapAlt = new Kategori();
		kitapAlt.setKirilim(2L);
		kitapAlt.setAdi("Kitap");
		kitapAlt.setUstKategori(kitap);

		categoryEntityService.save(kitapAlt);
	}

	private static void findAllUrunList(ProductEntityService productEntityService) {
		List<Urun> urunList = productEntityService.findAll();

		for (Urun urun : urunList) {
			System.out.println(urun.getAdi());
		}
	}

	private static void findAllUrunList(CategoryEntityService categoryEntityService) {
		List<Kategori> kategoriList = categoryEntityService.findAll();

		for (Kategori kategori : kategoriList) {
			System.out.println(kategori.getAdi());
		}
	}

	private static void deleteUrunList(ProductEntityService productEntityService) {
		List<Long> silinecekUrunIdlist = Arrays.asList(102L, 152L, 202L, 252L, 302L);

		for (Long urunId : silinecekUrunIdlist) {
			productEntityService.deleteById(urunId);
		}
	}

	private static void getSamsungM31(CategoryEntityService categoryEntityService, ProductEntityService productEntityService) {
		Kategori kategori = categoryEntityService.findById(502L);

		Urun urun = new Urun();
		urun.setAdi("Samsung M31");
		urun.setFiyat(new BigDecimal("3000"));
		urun.setKayitTarihi(new Date());
		urun.setKategori(kategori);

		urun = productEntityService.save(urun);

		System.out.println(urun);
	}

	private static Kategori getTelefonKategori(CategoryEntityService service) {
		Kategori ustKategori = service.findById(2L);

		System.out.println(ustKategori);

		Kategori kategori = new Kategori();
		kategori.setAdi("Telefon");
		kategori.setKirilim(2L);
		kategori.setUstKategori(ustKategori);

		kategori = service.save(kategori);

		System.out.println(kategori);

		return kategori;
	}

}
