package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http metodları RestController anotasyonu sayesinde kullanılır.
//put (olan kolonları günceller), patch (yeni kolon ekleyip de güncelleyebilir)

@AllArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandsController {

    private BrandService service;

    /*
    @Autowired //oto bağlama yapar(BrandService somut örneğine bağlar, oto newler)
    public BrandsController(BrandService service) {
        this.service = service;
    }
    */

    @GetMapping("/getAll")
    public List<GetAllBrandsResponse> findAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request) {
        return service.add(request);
    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Brand add(@RequestBody Brand brand) {
//        return service.add(brand);
//    }

@PutMapping("/{id}")
public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request) {
    return service.update(id, request);
}

//    @PutMapping("/{id}")
//    public Brand update(@PathVariable int id, @RequestBody Brand brand) {
//        return service.update(id, brand);
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
