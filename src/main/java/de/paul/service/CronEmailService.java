package de.paul.service;

import de.paul.dto.MailDTO;
import de.paul.util.Log;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * author: JP
 * date: 31.05.20
 */
@Singleton
public class CronEmailService {
private static final Log log = Log.getLog(CronEmailService.class);
    private List<MailDTO> emails;

    @Inject
    Mailer mailer;

    @PostConstruct
    void init() {
        this.emails = new ArrayList<>();
    }


//    @Scheduled(cron = "{cron.expression}")
    @Scheduled(every = "25s")
    void sendEmailCronJob() {
        for (MailDTO email : this.emails){
            log.info(email);
            mailer.send(Mail.withText(email.getRecipient(),email.getSubject(),email.getBody()));
        }
        this.emails.clear();
    }


    // getters and setters
    public List<MailDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<MailDTO> emails) {
        this.emails = emails;
    }
}
