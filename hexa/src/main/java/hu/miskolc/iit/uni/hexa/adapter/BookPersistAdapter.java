package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorRepository;
import hu.miskolc.iit.uni.hexa.adapter.persist.BookEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.BookRepository;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistBookPort;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import hu.miskolc.iit.uni.hexa.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookPersistAdapter implements PersistBookPort {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void saveBook(Book book) throws AuthorNotFoundException {
        Optional<AuthorEntity> author = authorRepository.findById(book.getAuthorId());
        saveBookIfAuthorIsPresent(book, author.orElse(null));
    }

    private void saveBookIfAuthorIsPresent(Book book, AuthorEntity author) throws AuthorNotFoundException {
        if (author != null) {
            BookEntity bookEntity = bookRepository.save(new BookEntity(null, book.getTitle(), author));
            log.debug("book saved: {}", bookEntity);
        } else {
            log.error("author not found ({})", book.getAuthorId());
            throw new AuthorNotFoundException("author id (" + book.getAuthorId() + ") does not exist");
        }
    }
}