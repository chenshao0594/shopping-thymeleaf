package com.shoppay.common.service;

import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.shoppay.common.config.ApplicationProperties;
import com.shoppay.common.user.User;
import com.shoppay.core.customer.Customer;

@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";

    private static final String BASE_URL = "baseUrl";

    private final ApplicationProperties appProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(ApplicationProperties appProperties, JavaMailSender javaMailSender,
            MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.appProperties = appProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(appProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, appProperties.getMail().getBaseUrl());
        String content = templateEngine.process("activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, appProperties.getMail().getBaseUrl());
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, appProperties.getMail().getBaseUrl());
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }
    
    @Async
    public void sendCustomerPasswordResetMail(Customer customer) {
        log.debug("Sending password reset email to '{}'", customer.getEmailAddress());
        Locale locale = customer.getMerchantStore().getLocale();
        Context context = new Context(locale);
        context.setVariable(USER, customer);
        context.setVariable(BASE_URL, appProperties.getMail().getBaseUrl());
        String content = templateEngine.process("customer-passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(customer.getEmailAddress(), subject, content, false, true);
    }
}
