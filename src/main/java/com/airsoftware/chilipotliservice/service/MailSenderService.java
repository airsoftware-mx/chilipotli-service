package com.airsoftware.chilipotliservice.service;


import com.airsoftware.chilipotliservice.model.EmailOrder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderService {

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private Configuration freemarkerConfiguration;

  private static final String DEFAULT_ENCODING = "utf-8";

  @Value("${spring.mail.username}")
  private String MAIL_FROM;

  @Value("${spring.mail.orders}")
  private String mailOrders;

  private void sendMail(String[] to, String subject, String text) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text, true);
    } catch (MessagingException e) {
      throw new MailParseException(e);
    }
    mailSender.send(message);
  }

  private String generateContent(String templateName, Map context) throws MessagingException {
    try {
      Template template = freemarkerConfiguration.getTemplate(templateName, DEFAULT_ENCODING);
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
    } catch (Exception e) {
      log.error("FreeMarker template doesn't exist", e);
      throw new MessagingException("FreeMarker template doesn't exist", e);
    }
  }

  public void sendEmailNewOrder(EmailOrder emailOrder) {
    try {
      Map context = new HashMap();
      context.put("restaurant", emailOrder.getRestaurant());
      context.put("orderDetail", emailOrder.getOrderDetail());
      context.put("customerName", emailOrder.getCustomerName());
      context.put("customerPhone", emailOrder.getCustomerPhone());
      context.put("delivery", emailOrder.getDelivery());
      context.put("address", emailOrder.getAddress());
      context.put("comments", emailOrder.getComments());
      context.put("subtotal", emailOrder.getSubtotal());
      String[] mailTo = mailOrders.split(";");
      sendMail(mailTo, "Nuevo Pedido " + emailOrder.getCustomerName(), generateContent("newOrder.ftl", context));
    } catch (final Exception e) {
      log.error("Error", e);
    }
  }

  public void sendTestEmail(String email, String content) {
    try {
      String[] mailTo = {email};
      sendMail(mailTo, "This is a test", content);
    } catch (final Exception e) {
      log.error("Error", e);
    }
  }

}
