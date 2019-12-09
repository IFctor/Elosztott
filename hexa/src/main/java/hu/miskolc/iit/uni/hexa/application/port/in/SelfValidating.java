package hu.miskolc.iit.uni.hexa.application.port.in;

import javax.validation.*;
import java.util.Set;

public class SelfValidating<T> {
    private Validator validator;

    public SelfValidating(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator=factory.getValidator();
    }

    protected void validateSelf(){
        Set<ConstraintViolation<T>> violation = validator.validate((T) this);
        if (!violation.isEmpty()){
            throw new ConstraintViolationException(violation);
        }
    }
}
