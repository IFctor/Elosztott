package hu.miskolc.uni.iit.fbbot.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author rpatra16
 * @since 30/08/2018
 */
@Service
public class FbApiEndpoints {

    @Value("${fbGraphApi}")
    @Getter
    private String facebookGraphApiUrl;

    @Value("${fbPageAccessToken}")
    private String pageAccessToken;

    public String getUserApiUrl() {
        return facebookGraphApiUrl + "/{userId}?access_token={token}";
    }

    public String getSubscribeUrl() {
        return facebookGraphApiUrl + "/me/subscribed_apps";
    }

    public String getFbSendUrl() {
        return facebookGraphApiUrl + "/me/messages?access_token=" + pageAccessToken;
    }

    public String getFbMessengerProfileUrl() {
        return facebookGraphApiUrl + "/me/messenger_profile?access_token={PAGE_ACCESS_TOKEN}";
    }
}