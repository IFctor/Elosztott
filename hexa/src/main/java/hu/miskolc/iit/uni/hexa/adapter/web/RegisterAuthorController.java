package hu.miskolc.iit.uni.hexa.adapter.web;

import hu.miskolc.iit.uni.hexa.adapter.RegisterAuthorResource;
import hu.miskolc.iit.uni.hexa.application.port.in.RegisterAuthorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterAuthorController {
    private final RegisterAuthorUseCase registerAuthorUseCase;

    @PostMapping("authors/register")
    void register(@RequestBody RegisterAuthorResource resource){
        registerAuthorUseCase.registerAuthor(resource.toCommand());
    }
}
