package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterAuthorUseCase;
import lombok.Data;

@Data
public class RegisterAuthorResource {
    private String name;

    public RegisterAuthorUseCase.RegisterAuthorCommand toCommand(){
        return new RegisterAuthorUseCase.RegisterAuthorCommand(name);
    }
}
