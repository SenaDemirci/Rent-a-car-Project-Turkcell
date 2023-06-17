package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.entities.enums.Status;
import kodlama.io.ecommerce.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final SaleRepository repository;
    private final ProductService service;

    public void checkIfSaleExistsById(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Sale.NotExists);
        }
    }
    public void checkIfProductExistsByStatus(int productId){
        if(Status.UNAVAILABLE.equals(service.getById(productId).getStatus())){
            throw new BusinessException(Messages.Sale.ProductNotExists);
        }
    }
    public void checkIfProductExistsByQuantity(int productId){
        int amount=service.getById(productId).getQuantity();
        if(amount<1){
            throw new BusinessException(Messages.Sale.NotStock);

        }
    }

}
