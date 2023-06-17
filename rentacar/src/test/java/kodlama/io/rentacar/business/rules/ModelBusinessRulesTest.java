package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.ModelRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ModelBusinessRulesTest {

    private ModelRepository repository;
    private ModelBusinessRules rules;
    @BeforeEach
    public void setUp(){
        repository= Mockito.mock(ModelRepository.class);
        rules=new ModelBusinessRules(repository);
    }


    @Test
    public void testCheckIfModelExists_withId_thenBusinessException(){
        int id=2;
        when(repository.existsById(id)).thenReturn(false);
        assertThrows(BusinessException.class,() ->rules.checkIfModelExists(id));

    }
    @Test
    public void testCheckIfModelExists_withId(){
        int id=2;
        when(repository.existsById(id)).thenReturn(true);
        rules.checkIfModelExists(id);
    }

    @Test
    public void testCheckIfModelExistsByName_withName_thenBusinessException(){
        String name="X3";

        when(repository.existsByNameIgnoreCase(name)).thenReturn(true);

        assertThrows(BusinessException.class,()->rules.checkIfModelExistsByName(name));
    }

    @Test
    public void testCheckIfModelExistsByName_withName(){
        String name="x4";

        when(repository.existsByNameIgnoreCase(name)).thenReturn(false);

        rules.checkIfModelExistsByName(name);
    }
}