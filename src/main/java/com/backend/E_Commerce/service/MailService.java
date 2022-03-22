package com.backend.E_Commerce.service;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String apiKey = "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";
    private static final Email fromAddress = new Email("emailintegration6@gmail.com");
    private static final String subject = "Sample Test";    
    private static final SendGrid sg = new SendGrid(apiKey);                

    public boolean sendEmail(String toAddress, String body) throws IOException{
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(fromAddress, subject, new Email(toAddress), content);
        Request req = new Request();
        
        try{
            req.setMethod(Method.POST);
            req.setEndpoint("mail/send");
            req.setBody(mail.build());

            Response res = sg.api(req);
            log.info(res.getBody());
            log.info(String.valueOf(res.getStatusCode()));
            return true;
        }
        catch(Exception e){
            throw e;
        }
    }    
}
