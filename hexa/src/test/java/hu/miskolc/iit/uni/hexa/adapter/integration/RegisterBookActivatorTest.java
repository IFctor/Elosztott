package hu.miskolc.iit.uni.hexa.adapter.integration;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.*;

public class RegisterBookActivatorTest {
    @Mock
    private RegisterBookUseCase registerBookUseCase;

    @InjectMocks
    private RegisterBookActivator registerBookActivator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(registerBookUseCase);
    }

    @Test
    void registerBook() throws AuthorNotFoundException {
        Message<RegisterBookUseCase.RegisterBookCommand> message = mock(Message.class);
        RegisterBookUseCase.RegisterBookCommand expected = new RegisterBookUseCase.RegisterBookCommand("Egy könyv címe", 1L);
        when(message.getPayload()).thenReturn(expected);
        registerBookActivator.registerBook(message);
        verify(registerBookUseCase).registerBook(expected);
    }
}
