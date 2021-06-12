package com.reddit.spring.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuild {
    private final TemplateEngine templateEngine;

    public MailContentBuild(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
