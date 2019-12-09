package hu.miskolc.iit.uni.hexa.service;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistBookPort;
import hu.miskolc.iit.uni.hexa.application.service.RegisterBookService;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import hu.miskolc.iit.uni.hexa.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class RegisterBookServiceTest {
    @Mock
    private PersistBookPort persistBookPort;

    @InjectMocks
    private RegisterBookService registerBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(persistBookPort);
    }

    @Test
    void registerBook() throws AuthorNotFoundException {
        RegisterBookUseCase.RegisterBookCommand expected = new RegisterBookUseCase.RegisterBookCommand("Könyvecském", 1L);
        registerBookService.registerBook(expected);
        verify(persistBookPort).saveBook(new Book("Könyvecském", 1L));
    }
}
