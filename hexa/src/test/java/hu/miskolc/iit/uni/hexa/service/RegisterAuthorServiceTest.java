package hu.miskolc.iit.uni.hexa.service;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterAuthorUseCase;
import hu.miskolc.iit.uni.hexa.application.port.out.PersistAuthorPort;
import hu.miskolc.iit.uni.hexa.application.service.RegisterAuthorService;
import hu.miskolc.iit.uni.hexa.domain.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class RegisterAuthorServiceTest {
    @Mock
    private PersistAuthorPort persistAuthorPort;

    @InjectMocks
    private RegisterAuthorService registerAuthorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(persistAuthorPort);
    }

    @Test
    void registerAuthor() {
        registerAuthorService.registerAuthor(new RegisterAuthorUseCase.RegisterAuthorCommand("Kiss János"));
        verify(persistAuthorPort).saveAuthor(new Author("Kiss János"));
    }
}
