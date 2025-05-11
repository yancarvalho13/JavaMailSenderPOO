package com.yan.mailsender.domain.ports.output;

import com.yan.mailsender.domain.model.Email;

public interface EmailSenderOutput {
  void deliver(Email email);
}
