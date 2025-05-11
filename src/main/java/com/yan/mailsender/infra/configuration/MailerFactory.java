package com.yan.mailsender.infra.configuration;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailerFactory {
  @Value("${smtp.host}") public static String host;
  @Value("${smtp.port}") public static int port;
  @Value("${smtp.user}") public static String username;
  @Value("${smtp.pass}") public static String password;
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
