package hu.miskolc.iit.uni.hexa.adapter.integration;

import hu.miskolc.iit.uni.hexa.application.port.in.RegisterAuthorUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.*;

public class RegisterAuthorActivatorTest {
    @Mock
    private RegisterAuthorUseCase registerAuthorUseCase;

    @InjectMocks
    private RegisterAuthorActivator registerAuthorActivator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(registerAuthorUseCase);
    }

    @Test
    void registerAuthor() {
        Message<RegisterAuthorUseCase.RegisterAuthorCommand> message = mock(Message.class);
        RegisterAuthorUseCase.RegisterAuthorCommand expected = new RegisterAuthorUseCase.RegisterAuthorCommand("Sajt Péter");
        when(message.getPayload()).thenReturn(expected);
        registerAuthorActivator.registerAuthor(message);
        verify(registerAuthorUseCase).registerAuthor(expected);
    }
}
