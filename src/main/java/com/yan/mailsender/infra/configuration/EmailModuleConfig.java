package com.yan.mailsender.infra.configuration;

import com.yan.mailsender.domain.ports.input.EmailSenderInput;
import com.yan.mailsender.domain.useCase.EmailUseCase;
import com.yan.mailsender.infra.adapter.SimpleJavaMailSenderAdapter;
import org.simplejavamail.api.mailer.Mailer;

public class EmailModuleConfig {
  private EmailModuleConfig() {}

  public static EmailSenderInput simpleJavaMail(Mailer mailer) {
    return new EmailUseCase(new SimpleJavaMailSenderAdapter(mailer));
  }

  public static EmailSenderInput defaultFromEnv(){
    Mailer mailer = MailerFactory.fromEnv();
    return simpleJavaMail(mailer);
  }
}
