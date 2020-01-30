package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import org.springframework.http.ResponseEntity;

public interface FbWebRequest {
    ResponseEntity<String> getResponseEntity(Event event);
}