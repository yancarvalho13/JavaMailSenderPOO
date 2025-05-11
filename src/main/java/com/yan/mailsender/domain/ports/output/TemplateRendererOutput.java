package com.yan.mailsender.domain.ports.output;

import java.util.Map;

public interface TemplateRendererOutput {
  String render(String template, Map<String, Object> model);
}
