package com.yan.mailsender.domain.model;

import java.util.regex.Pattern;

public record EmailAddress(String value) {
  private static final Pattern SIMPLE =
          Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

  public EmailAddress {
    if(!SIMPLE.matcher(value).matches()) {
      throw new IllegalArgumentException("Invalid email adress");
    }
  }

  public EmailAddress of(String emailAddress) {
    return new EmailAddress(emailAddress);
  }
}
