package com.yan.mailsender;

import com.yan.mailsender.domain.model.Email;
import com.yan.mailsender.domain.model.EmailAddress;
import com.yan.mailsender.domain.ports.input.EmailSenderInput;
import com.yan.mailsender.domain.ports.output.EmailSenderOutput;
import com.yan.mailsender.domain.useCase.EmailUseCase;
import com.yan.mailsender.infra.adapter.SimpleJavaMailSenderAdapter;
import com.yan.mailsender.infra.configuration.EmailModuleConfig;
import com.yan.mailsender.infra.configuration.MailerFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.List;
import java.util.Optional;

public class testing {

  @Test void buildsEmailCorrectly(){
    Email e = new Email(
            new EmailAddress("no-reply@demo.com"),
            List.of(new EmailAddress("alice@foo.com")),
            "Assunto",
            "<p>Corpo</p>"
    );
    assert ("Assunto".equals(e.subject()));
  }

  @Test void delegatesToPort(){
    EmailSenderOutput port = Mockito.mock(EmailSenderOutput.class);
    Email e = new Email(
            new EmailAddress("no-reply@demo.com"),
            List.of(new EmailAddress("alice@foo.com")),
            "oi",
            "<p>Olá</p>"
    );
    new EmailUseCase(port).send(e, Optional.empty());
    Mockito.verify(port).deliver(e);
  }

  @Test void CallsMailerWithCorrectEmail(){
    Mailer mailer = Mockito.mock(Mailer.class);
    Email e = new Email(
            new EmailAddress("no-reply@demo.com"),
            List.of(new EmailAddress("alice@foo.com")),
            "Assunto",
            "<p>Corpo</p>"
    );
    EmailSenderOutput port = new SimpleJavaMailSenderAdapter(mailer);
    port.deliver(e);
    Mockito.verify(mailer).sendMail(Mockito.any());

  }
  @Test void ShoudSendEmailCorrectly(){
    Mailer mailer = Mockito.mock(Mailer.class);
    EmailSenderInput emailUseCase = EmailModuleConfig.simpleJavaMail(mailer);
    Email email = new Email(
            new EmailAddress("yansilva303@gmail.com"),
            List.of(new EmailAddress("yan.carvalho@ucsal.edu.br")),
            "Assunto teste",
            "<p>corpo</p>");
    emailUseCase.send(email, Optional.empty());
    Email email2 = Mockito.mock(Email.class);

    Mockito.verify(mailer).sendMail(Mockito.any(org.simplejavamail.api.email.Email.class));

  }
}
