package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.facebook.models.Message;
import hu.miskolc.uni.iit.fbbot.facebook.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FBServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FbApiEndpoints fbApiEndpoints;

    @Mock
    private FbWebRequest fbWebRequest;

    @InjectMocks
    private FBServiceImpl fbService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendResponse_sendsResponse() {
        Mockito.when(fbApiEndpoints.getFbSendUrl()).thenReturn("FB_URL");
        Mockito.when(fbWebRequest.getResponseEntity(Mockito.any(Event.class)))
                .thenReturn(ResponseEntity.ok("sajt"));

        assertEquals(ResponseEntity.ok("sajt"), fbService.sendResponse(new Event()));
        Mockito.verify(fbWebRequest).getResponseEntity(Mockito.any(Event.class));

        Mockito.reset(fbApiEndpoints, restTemplate, fbWebRequest);
    }

    @Test
    void sendResponse_userIsNotLost() {
        Event event = new Event();
        Message message = new Message().setText("hello");
        User user = new User().setFirstName("Viktor");
        event.setMessage(message).setRecipient(user);
        Mockito.when(fbApiEndpoints.getFbSendUrl()).thenReturn("FB_URL");
        Mockito.when(fbWebRequest.getResponseEntity(Mockito.any(Event.class)))
                .thenReturn(ResponseEntity.ok("sajt"));

        assertEquals(ResponseEntity.ok("sajt"), fbService.sendResponse(event));

        Mockito.reset(fbApiEndpoints, restTemplate, fbWebRequest);
    }
}