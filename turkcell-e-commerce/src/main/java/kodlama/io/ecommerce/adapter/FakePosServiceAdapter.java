package kodlama.io.ecommerce.adapter;

import kodlama.io.ecommerce.business.abstracts.PosService;
import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import org.hibernate.query.results.PositionalSelectionsNotAllowedException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful=new Random().nextBoolean();
        if(!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}
