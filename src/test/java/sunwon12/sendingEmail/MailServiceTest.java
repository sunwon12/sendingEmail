package sunwon12.sendingEmail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {MailConfig.class, MailService.class})
class MailServiceTest {

    @Autowired
    MailService mailService;
    @Value("${email.subject}")
    String subject;

    @Value("${email.content}")
    String content;

    String to = "jsw5913@naver.com";

    @Test
    @DisplayName("테스트 이메일 발송입니다")
    public void MailsendTest() {
        mailService.sendMail(to, "제목입니다", "내용입니다");
    }

}