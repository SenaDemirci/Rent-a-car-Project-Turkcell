package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sales")
public class SalesController {
    private final SaleService service;

    @GetMapping
    public List<GetAllSalesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSaleResponse add(@Valid @RequestBody CreateSaleRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateSaleResponse update(@PathVariable int id, @Valid @RequestBody UpdateSaleRequest request){
        return service.update(id,request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
