package hu.miskolc.iit.uni.hexa.application.port.in;

import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface RegisterBookUseCase {
    void registerBook(RegisterBookCommand command) throws AuthorNotFoundException;

    @Getter
    @ToString
    final class RegisterBookCommand extends SelfValidating {
        @NotBlank
        private final String title;
        @NotNull
        private final Long authorId;

        public RegisterBookCommand(String title, Long authorId) {
            this.title = title;
            this.authorId = authorId;
            validateSelf();
        }
    }
}