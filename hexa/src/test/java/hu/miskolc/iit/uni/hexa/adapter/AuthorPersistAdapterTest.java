package hu.miskolc.iit.uni.hexa.adapter;

import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorEntity;
import hu.miskolc.iit.uni.hexa.adapter.persist.AuthorRepository;
import hu.miskolc.iit.uni.hexa.domain.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class AuthorPersistAdapterTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorPersistAdapter authorPersistAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(authorRepository);
    }

    @Test
    void saveAuthor() {
        authorPersistAdapter.saveAuthor(new Author("Kiss János"));
        verify(authorRepository).save(new AuthorEntity(null, "Kiss János"));
    }
}
