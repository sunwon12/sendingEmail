package sunwon12.sendingEmail;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${email.id}")
    private String fromId;

    private int authNumber;

    private  final JavaMailSender mailSender;

    @Transactional
    public int sendCodeEmail(String email) {
        makeRandomNumber();
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 !
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
        sendMail(email, title, content);
        return authNumber;
    }
    private void makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        authNumber = checkNum;
    }

    private void sendMail(String to, String subject, String body) {
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
