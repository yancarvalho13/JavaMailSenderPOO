package com.yan.mailsender.domain.useCase;

import com.yan.mailsender.domain.model.Email;
import com.yan.mailsender.domain.ports.input.EmailSenderInput;
import com.yan.mailsender.domain.ports.output.EmailSenderOutput;

import java.util.Optional;

public class EmailUseCase implements EmailSenderInput {

  private final EmailSenderOutput output;

  public EmailUseCase(EmailSenderOutput output) {
    this.output = output;
  }

  @Override
  public void send(Email email, Optional<Object> data) {
    output.deliver(email);
  }
}
