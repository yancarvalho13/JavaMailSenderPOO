package com.yan.mailsender.domain.ports.input;

import com.yan.mailsender.domain.model.Email;

import java.util.Optional;

public interface EmailSenderInput {
  void send(Email email, Optional<Object> data);
}
