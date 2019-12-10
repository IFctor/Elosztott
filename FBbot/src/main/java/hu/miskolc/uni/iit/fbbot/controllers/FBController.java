package hu.miskolc.uni.iit.fbbot.controllers;

import hu.miskolc.uni.iit.fbbot.facebook.models.*;
import hu.miskolc.uni.iit.fbbot.services.FBService;
import hu.miskolc.uni.iit.fbbot.services.RoBotService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FBController {

    @Value("${fbBotToken}")
    @Getter
    private String fbToken;

    @Value("${fbPageAccessToken}")
    @Getter
    private String pageAccessToken;

    private final FBService fbService;

    private final RoBotService roBotService;

    @GetMapping("webhook")
    public final ResponseEntity getWebhook(@RequestParam("hub.mode") String mode,
                                           @RequestParam("hub.verify_token") String verifyToken,
                                           @RequestParam("hub.challenge") String challenge) {
        if (EventType.SUBSCRIBE.name().equalsIgnoreCase(mode) && getFbToken().equals(verifyToken)) {
            return ResponseEntity.ok(challenge);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ResponseBody
    @PostMapping(value = "webhook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity postWebhook(@RequestBody Callback callback) {

        if (!callback.getObject().equals("page")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Entry entry : callback.getEntry()) {
            for (Event event : entry.getMessaging()) {
                if (event.getMessage() != null) {
                    event.setType(EventType.MESSAGE);
                    fbService.sendResponse(roBotService.getResponse(event));
                }
            }
        }
        return ResponseEntity.ok("EVENT_RECEIVED");
    }
}
