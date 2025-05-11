package com.yan.mailsender.infra.configuration;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailerFactory {
public static String host = System.getenv("SMTP_HOST");
public static Integer port = Integer.valueOf(System.getenv("SMTP_PORT"));
public static String username = System.getenv("SMTP_USERNAME");
public static String password = System.getenv("SMTP_PASS");
  private MailerFactory() {}

  public  static Mailer fromEnv(){
    return MailerBuilder
            .withSMTPServer(host,port,username,password)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .buildMailer();
  }

  public static Mailer of(String host, int port, String username, String password) {
    return MailerBuilder
            .withSMTPServer(host,port,username,password)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .buildMailer();
  }
}
