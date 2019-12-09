package hu.miskolc.iit.uni.hexa.application.service;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistBookPort;
import hu.miskolc.iit.uni.hexa.common.UseCase;
import hu.miskolc.iit.uni.hexa.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RegisterBookService implements RegisterBookUseCase {
    private final PersistBookPort persistBookPort;

    @Override
    public void registerBook(RegisterBookUseCase.RegisterBookCommand command) {
        log.debug("incoming registerBook {}", command);
        persistBookPort.saveBook(new Book(command.getTitle(), command.getAuthorId()));
    }
}