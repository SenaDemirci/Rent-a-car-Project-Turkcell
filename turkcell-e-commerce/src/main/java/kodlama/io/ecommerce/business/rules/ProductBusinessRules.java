package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private ProductRepository repository;

    public  void checkIfProductExistsById(int id){
        if(!repository.existsById(id)) {
            throw new BusinessException(Messages.Product.NotExists);
        }
    }
    public void validateProduct(String description,double unitPrice,int quantity) {
        checkIfUnitPriceValid(unitPrice);
        checkIfQuantityValid(quantity);
        checkIfDescriptionLengthValid(description);
    }
    public void checkIfDescriptionLengthValid(String description) {
        if (description.length() < 10 || description.length() > 50)
            throw new BusinessException(Messages.Product.DescriptionLengthValid);
    }

    public void checkIfUnitPriceValid(double unitPrice) {
        if (unitPrice <= 0)
            throw new BusinessException(Messages.Product.UnitPriceValid);
    }

    public void checkIfQuantityValid(int quantity) {
        if (quantity < 0) throw new BusinessException(Messages.Product.QuantityValid);
    }
}
