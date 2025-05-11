package com.yan.mailsender;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.yan.mailsender.domain.model.Email;
import com.yan.mailsender.domain.model.EmailAddress;
import com.yan.mailsender.domain.ports.input.EmailSenderInput;
import com.yan.mailsender.infra.configuration.EmailModuleConfig;
import com.yan.mailsender.infra.configuration.MailerFactory;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.List;
import java.util.Optional;

public class demo {
  public static void main(String[] args) {
    Mailer mailer = MailerFactory.fromEnv();
    EmailSenderInput port = EmailModuleConfig.simpleJavaMail(mailer);
    Email email = new Email(new EmailAddress("yansilva303@gmail.com"),
            List.of(new EmailAddress("yan.carvalho@ucsal.edu.br")),
            "Teste Simple Java Mail – Funciona!",
            "<h3>Parabéns 🎉</h3><p>Seu adapter enviou este e-mail.</p>");
    port.send(email, Optional.empty());
    System.out.println("Email sent!");
  }
}
