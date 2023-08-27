package sunwon12.sendingEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${email.id}")
    private String fromId;

    private  final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body) {
        MimeMessagePreparator messagePreparator =
                mimeMessage -> {
            //true는 멀티파트 메세지를 사용하겠다는 의미
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom(fromId);
                    helper.setTo(to);
                    helper.setSubject(subject);
                    helper.setText(body, true);
                };
        mailSender.send(messagePreparator);
    }
}
