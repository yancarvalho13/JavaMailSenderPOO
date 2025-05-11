package com.yan.mailsender.domain.model;

import java.util.List;
import java.util.Objects;

public record Email(
        EmailAddress from,
        List<EmailAddress> to,
        String subject,
        String htmlBody) {

  public Email {
    Objects.requireNonNull(from, "Remetente é obrigatório");
    Objects.requireNonNull(to,   "Destinatário é obrigatório");
    if (to.isEmpty()) throw new IllegalArgumentException("Lista de destinatários vazia");
    Objects.requireNonNull(subject,  "Assunto é obrigatório");
    Objects.requireNonNull(htmlBody, "Corpo do email é obrigatório");
  }
}
