package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public Brand getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand) {
        return service.add(brand);
    }
    @PutMapping("/{id}")
    public Brand update(@PathVariable int id, @RequestBody Brand brand) {
        return service.update(id, brand);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
