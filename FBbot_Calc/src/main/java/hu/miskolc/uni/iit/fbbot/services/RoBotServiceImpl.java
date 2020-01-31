package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.controllers.Calculator;
import hu.miskolc.uni.iit.fbbot.facebook.models.Message;
import hu.miskolc.uni.iit.fbbot.facebook.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoBotServiceImpl implements RoBotService {

    @Override
    public Event getResponse(Event event) {
        Event response = new Event()
                .setMessagingType("RESPONSE")
                .setRecipient(event.getSender());
        String message = "";
        if (event.getMessage() != null) {
            message = event.getMessage().getText();
        }

        if ("hello".equals(message)) {
            response.setMessage(new Message().setText("hello"));
        } else if (message.startsWith("?")) {
            Calculator calculator = new Calculator();
            String subMessage = message.substring(1);
            if (calculator.isValidInput(subMessage)) {
                if (calculator.hasDividebyZero(subMessage) == false) {
                    double result = calculator.calculate(subMessage);
                    response.setMessage(new Message().setText("A válaszom: " + result));
                } else {
                    response.setMessage(new Message().setText("Van benne nullával való osztás"));
                }
            } else {
                response.setMessage(new Message().setText("ezt nem tudom kiszamolni formai hiba miatt"));
            }

        } else {
            response.setMessage(new Message().setText("nem tudom mit akarsz"));
        }
        return response;
    }
}
