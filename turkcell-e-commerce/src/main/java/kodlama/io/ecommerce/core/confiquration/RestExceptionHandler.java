package kodlama.io.ecommerce.core.confiquration;

import kodlama.io.ecommerce.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handlerException(BusinessException exception){
        return "BUSINESS_EXCEPTION\n"+ exception.getMessage();
    }
}
