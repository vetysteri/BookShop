package wad.controller;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.Account;
import wad.repository.AccountRepository;

@Controller
public class DefaultController {

    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/books";
    }
}
