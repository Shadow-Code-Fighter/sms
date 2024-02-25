package com.smsservices.controller;

import com.smsservices.payload.SMSRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {

    @Value("${twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;

    @PostMapping("/send-sms")
    public String sendSMS(@RequestBody SMSRequest request) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        Message message = Message.creator(
                        new PhoneNumber(request.getTo()),
                        new PhoneNumber(request.getFrom()),
                        request.getBody())
                .create();

        return message.getSid(); // Return the message SID or any appropriate response
    }
}
