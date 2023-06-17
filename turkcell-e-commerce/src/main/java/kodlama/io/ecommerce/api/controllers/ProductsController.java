package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.create.CreateProductRequest;

import kodlama.io.ecommerce.business.dto.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeStatus){
        return service.getAll(includeStatus);
    }
    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@Valid  @RequestBody CreateProductRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@PathVariable int id,@Valid @RequestBody UpdateProductRequest request){
        return service.update(id,request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
