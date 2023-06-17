package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalBusinessRulesTest {

    private RentalBusinessRules rules;
    private RentalRepository repository;

    @BeforeEach
    public void setUp(){
        repository= Mockito.mock(RentalRepository.class);
        rules=new RentalBusinessRules(repository);
    }

    @Test
    public void testCheckIfRentalExists_withId_thenBusinessException(){
        int id=1;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(BusinessException.class,() ->rules.checkIfRentalExists(id));

    }
    @Test
    public void testCheckIfRentalExists_withId(){
        int id=1;

        when(repository.existsById(id)).thenReturn(true);

        rules.checkIfRentalExists(id);

    }
    @Test
    public void testCheckIfCarAvailable_withState_thenBusinessException(){
        State state=State.RENTED;

        assertThrows(BusinessException.class,()->rules.checkIfCarAvailable(state));
    }
    @Test
    public void testCheckIfCarAvailable_withState(){
        State state=State.AVAILABLE;

        rules.checkIfCarAvailable(state);
    }
}