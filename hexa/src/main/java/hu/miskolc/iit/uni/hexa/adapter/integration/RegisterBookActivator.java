package hu.miskolc.iit.uni.hexa.adapter.integration;


import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@Slf4j
@RequiredArgsConstructor
public class RegisterBookActivator {

    private final RegisterBookUseCase registerBookUseCase;

    @ServiceActivator(inputChannel = "authorChannel")
    public void registerBook(Message<RegisterBookUseCase.RegisterBookCommand> msg) throws AuthorNotFoundException {
        RegisterBookUseCase.RegisterBookCommand payload = msg.getPayload();
        log.debug("Comming: {}", payload.toString());
        registerBookUseCase.registerBook(payload);
    }
}
