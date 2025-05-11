package com.yan.mailsender.infra.adapter;

import com.yan.mailsender.domain.model.Email;
import com.yan.mailsender.domain.model.EmailAddress;
import com.yan.mailsender.domain.ports.output.EmailSenderOutput;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;

import java.util.stream.Collectors;

public class SimpleJavaMailSenderAdapter implements EmailSenderOutput {
  private final Mailer mailer;

  public SimpleJavaMailSenderAdapter(Mailer mailer) {
    this.mailer = mailer;
  }

  @Override
  public void deliver(Email email) {
    org.simplejavamail.api.email.Email simpleEmail =
            EmailBuilder.startingBlank()
                    .from(email.from().value())
                    .to(
                            email.to().stream()
                                    .map(EmailAddress::value)
                                    .collect(Collectors.joining(", "))
                    )
                    .withSubject(email.subject())
                    .withHTMLText(email.htmlBody())
                    .buildEmail();

    mailer.sendMail(simpleEmail);
  }
}
