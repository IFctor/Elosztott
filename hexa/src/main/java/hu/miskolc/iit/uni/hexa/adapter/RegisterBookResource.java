package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import lombok.Data;

@Data
public class RegisterBookResource {
    private String title;
    private Long authorId;

    public RegisterBookUseCase.RegisterBookCommand toCommand() {
        return new RegisterBookUseCase.RegisterBookCommand(title, authorId);
    }
}