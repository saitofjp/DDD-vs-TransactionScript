package com.example.demo.mail.impl;

import com.example.demo.mail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMaileServiceImpl implements SendMailService {

  @Autowired
  private MailSender mailSender;

  @Override
  public void sendEmail(SimpleMailMessage message) {
    mailSender.send(message);
  }
}
