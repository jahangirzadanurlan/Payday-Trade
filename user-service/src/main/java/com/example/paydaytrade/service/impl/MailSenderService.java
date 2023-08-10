package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.entity.ConfirmationToken;
import com.example.paydaytrade.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email, ConfirmationToken confirmationToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(email);
            helper.setSubject("Confirm account!");

            String confirmationLink = "http://localhost:8081/auth/confirm/" + confirmationToken.getToken();
            String emailContent = "<html><body><p>Please click the following link to confirm your account:</p>"
                    + "<a href=\"" + confirmationLink + "\">Confirm Account</a>"
                    + "</body></html>";

            helper.setText(emailContent, true);

            javaMailSender.send(message);
            log.info("Email sent: " + email);
        } catch (Exception e) {
            // Handle exception
        }
    }

    public void sendBuyMessage(User user, BuySellRequestDto buySellRequestDto, int price){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        LocalDateTime transactionDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTransactionDate = transactionDate.format(formatter);

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Notification Regarding Your Recent Stock Purchase");
        simpleMailMessage.setText("Hello " + user.getUsername() + ",\n\n" +
                "We wanted to inform you about your recent stock purchase with the symbol " + buySellRequestDto.getSymbol() + ". Below are the details of the transaction:\n\n" +
                "Stock symbol: " + buySellRequestDto.getSymbol() + "\n" +
                "Offer Price: " + buySellRequestDto.getOfferPrice() + "\n" +
                "Purchase amount: " + price + "\n" +
                "Transaction Date: " + formattedTransactionDate + "\n\n" +
                "If you have any questions or need further assistance regarding this purchase, please don't hesitate to contact us. We are always here to help.\n\n" +
                "Thank you");

        javaMailSender.send(simpleMailMessage);
        log.info("Email sent: " + user.getEmail());
    }

    public void sendSellMessage(User user, BuySellRequestDto buySellRequestDto, int price){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        LocalDateTime transactionDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTransactionDate = transactionDate.format(formatter);

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Notification Regarding Your Recent Stock Sale");
        simpleMailMessage.setText("Hello " + user.getUsername() + ",\n\n" +
                "We wanted to inform you about your recent stock sale with the symbol " + buySellRequestDto.getSymbol() + ". Below are the details of the transaction:\n\n" +
                "Stock symbol: " + buySellRequestDto.getSymbol() + "\n" +
                "Offer Price: " + buySellRequestDto.getOfferPrice() + "\n" +
                "Sold amount: " + price + "\n" +
                "Transaction Date: " + formattedTransactionDate + "\n\n" +
                "If you have any questions or need further assistance regarding this purchase, please don't hesitate to contact us. We are always here to help.\n\n" +
                "Thank you");

        javaMailSender.send(simpleMailMessage);
        log.info("Email sent: " + user.getEmail());
    }

}
