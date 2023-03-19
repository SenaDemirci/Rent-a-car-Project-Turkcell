package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//http metodları RestController anotasyonu sayesinde kullanılır.
//put (olan kolonları günceller), patch (yeni kolon ekleyip de güncelleyebilir)

@RestController
@RequestMapping("/brands")
public class BrandsController {
    private BrandService service;

    @Autowired //oto bağlama yapar(BrandService somut örneğine bağlar, oto newler)
    public BrandsController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Brand> findAll() {
        return service.getAll();
    }

}
