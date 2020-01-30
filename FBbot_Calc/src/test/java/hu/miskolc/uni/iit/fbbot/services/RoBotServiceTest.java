package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.facebook.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoBotServiceTest {

    private RoBotService roBotService;

    @BeforeEach
    public void setUp() {
        roBotService = new RoBotServiceImpl();
    }

    @Test
    void getResponse_hello_input_hello_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("hello"));
        assertEquals("hello", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_valamimas_input_nemtudommitakarsz_output() {
        Event event = new Event();
        event.setMessage(new Message().setText("valami más"));
        assertEquals("nem tudom mit akarsz", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_null_input_nemtudommitakarsz_output() {
        Event event = new Event();

        event.setMessage(null);
        assertEquals("nem tudom mit akarsz", roBotService.getResponse(event).getMessage().getText());
    }
}
