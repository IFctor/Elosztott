package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorRepository;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistAuthorPort;
import hu.miskolc.iit.uni.hexa.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorPersistAdapter implements PersistAuthorPort {

    private final AuthorRepository authorRepository;
    @Override
    public void saveAuthor(Author author){
        AuthorEntity authorEntity = authorRepository.save(new AuthorEntity(null,author.getName()));
        log.debug("entity saved {}", authorEntity);
    }
}
