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

    @Test
    void getResponse_8_input_8_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?8"));
        assertEquals("A válaszom: 8", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_12o58_input_70_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?12+58"));
        assertEquals("A válaszom: 70", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_0m25_input_m25_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?0-25"));
        assertEquals("A válaszom: -25", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_0m25o85_input_60_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?0-25+85"));
        assertEquals("A válaszom: 60", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_0o25m85_input_m60_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?0+25-85"));
        assertEquals("A válaszom: -60", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_0mu25di85_input_0_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?0*25/85"));
        assertEquals("A válaszom: 0", roBotService.getResponse(event).getMessage().getText());
    }

    @Test
    void getResponse_0di85_input_0_output() {
        Event event = new Event();

        event.setMessage(new Message().setText("?0/85"));
        assertEquals("A válaszom: 0", roBotService.getResponse(event).getMessage().getText());
    }
}
