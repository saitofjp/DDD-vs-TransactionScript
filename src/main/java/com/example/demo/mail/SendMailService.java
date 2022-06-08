package com.example.demo.mail;


import com.example.demo.exception.AppSendMailException;
import org.springframework.mail.SimpleMailMessage;

public interface SendMailService {

  void sendEmail(SimpleMailMessage message) throws AppSendMailException;
}
