package hu.miskolc.iit.uni.hexa.adapter.web;


import hu.miskolc.iit.uni.hexa.adapter.RegisterBookResource;
import hu.miskolc.iit.uni.hexa.application.port.in.RegisterBookUseCase;
import hu.miskolc.iit.uni.hexa.domain.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterBookController {

    private final RegisterBookUseCase registerBookUseCase;

    @PostMapping("books/register")
    void register(@RequestBody RegisterBookResource registerBookResource) throws AuthorNotFoundException {
        registerBookUseCase.registerBook(registerBookResource.toCommand());
    }
}