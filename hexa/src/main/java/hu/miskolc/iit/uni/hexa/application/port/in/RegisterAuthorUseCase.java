package hu.miskolc.iit.uni.hexa.application.port.in;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

public interface RegisterAuthorUseCase {
    void registerAuthor (RegisterAuthorCommand command);

    @Getter
    @ToString
    final class RegisterAuthorCommand extends SelfValidating{
        @NotBlank
        private final String name;

        public RegisterAuthorCommand(String name)
        {
            this.name=name;
            validateSelf();
        }
    }
}
