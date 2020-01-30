package hu.miskolc.uni.iit.fbbot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.miskolc.uni.iit.fbbot.facebook.models.Callback;
import hu.miskolc.uni.iit.fbbot.facebook.models.Entry;
import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.facebook.models.Message;
import hu.miskolc.uni.iit.fbbot.services.FBService;
import hu.miskolc.uni.iit.fbbot.services.RoBotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(FBController.class)
class FBControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FBService facebookService;

    @MockBean
    private RoBotService facebookBotService;

    @Value("${fbBotToken}")
    private String verifyToken;

    @BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void givenWac_whenServletContext_thenItProvidesFBController() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("FBController"));
    }

    @Test
    void getWebhook() throws Exception {
        String uri = "/webhook";
        String challenge = "falevél lenni az őszben";
        mvc.perform(MockMvcRequestBuilders
                .get("/webhook")
                .param("hub.mode", "SUBSCRIBE")
                .param("hub.verify_token", verifyToken)
                .param("hub.challenge", challenge))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(challenge));

        mvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    void postWebhook() throws Exception {
        String uri = "/webhook";
        Callback callback = new Callback();
        callback.setObject("falevél lenni az őszben");
        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(callback)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        callback = new Callback();
        callback.setObject("page");
        Entry[] entries = new Entry[1];
        Entry entry = new Entry();
        Event[] events = new Event[1];
        Event event = new Event();
        event.setMessage(new Message());
        events[0] = event;
        entry.setMessaging(events);
        entries[0] = entry;
        callback.setEntry(entries);

        Mockito.when(facebookService.sendResponse(Mockito.any(Event.class))).thenReturn(ResponseEntity.ok().build());
        Mockito.when(facebookBotService.getResponse(Mockito.any(Event.class))).thenReturn(new Event());

        mvc.perform(MockMvcRequestBuilders
                .post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(callback)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(facebookService, Mockito.times(1)).sendResponse(Mockito.any(Event.class));
        Mockito.verify(facebookBotService, Mockito.times(1)).getResponse(Mockito.any(Event.class));
    }
}
