package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorRepository;
import hu.miskolc.iit.uni.hexa.adapter.persist.BookEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.BookRepository;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import hu.miskolc.iit.uni.hexa.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookPersistAdapterTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookPersistAdapter bookPersistAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(authorRepository, bookRepository);
    }

    @Test
    void saveBook_existingAuthor_works() throws AuthorNotFoundException {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(new AuthorEntity(1L, "Kiss János")));
        bookPersistAdapter.saveBook(new Book("Valami cim", 1L));
        verify(authorRepository).findById(1L);
        verify(bookRepository).save(new BookEntity(null, "Valami cim", new AuthorEntity(1L, "Kiss János")));
    }

    @Test
    void saveBook_missingAuthor_throwsAuthorNotFoundException() throws AuthorNotFoundException {
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> bookPersistAdapter.saveBook(new Book("Másik cim", 2L)));
        verify(authorRepository).findById(2L);
    }
}
