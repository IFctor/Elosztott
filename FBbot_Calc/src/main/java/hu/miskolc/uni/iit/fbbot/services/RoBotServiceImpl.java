package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.controllers.Calculator;
import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.facebook.models.Message;
import org.springframework.stereotype.Service;

@Service
public class RoBotServiceImpl implements RoBotService {

    @Override
    public Event getResponse(Event event) {
        Event response = new Event()
                .setMessagingType("RESPONSE")
                .setRecipient(event.getSender());
        String message = event.getMessage().getText();
        if (event.getMessage() != null && "hello".equals(message)) {
            response.setMessage(new Message().setText("hello"));
        }
        else if(event.getMessage() != null && message.startsWith("?")) {
            Calculator calculator =new Calculator();
            if(calculator.isValidInput(message.substring(1))){
                int result = calculator.calculate(message.substring(1));
                response.setMessage(new Message().setText("A válaszom: "+result));
            }else {
                response.setMessage(new Message().setText("ezt nem tudom kiszamolni formai hiba miatt"));
            }

        }
        else {
            response.setMessage(new Message().setText("nem tudom mit akarsz"));
        }
        return response;
    }
}
