package sunwon12.sendingEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailAPI {

    private final MailService mailService;

    @PostMapping("/send")
    public void sendEmail() {

    }
}
