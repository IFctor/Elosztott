package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.facebook.models.Event;

public interface RoBotService {

    Event getResponse(Event event);
}