package hu.miskolc.iit.uni.hexa.application.service;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterAuthorUseCase;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistAuthorPort;
import hu.miskolc.iit.uni.hexa.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterAuthorService implements RegisterAuthorUseCase {
    private final PersistAuthorPort persistAuthorPort;

    @Override
    public void registerAuthor(RegisterAuthorCommand command){
        log.debug("incoming registerAuthor {}",command);
        persistAuthorPort.saveAuthor(new Author(command.getName()));
    }
}
