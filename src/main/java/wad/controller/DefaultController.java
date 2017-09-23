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

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepo;
    
    @PostConstruct
    public void init (){
        
       Account larry = new Account();
       larry.setUsername("larry");
       larry.setPassword(passwordEncoder.encode("larry"));
       larry.setAuthorities(Arrays.asList("USER"));
       accountRepo.save(larry);
       
       Account moe = new Account();       
       moe.setUsername("moe");
       moe.setPassword(passwordEncoder.encode("moe"));
       moe.setAuthorities(Arrays.asList("USER", "ADMIN"));
       accountRepo.save(moe);
       
       Account curly = new Account();
       curly.setUsername("curly");
       curly.setPassword(passwordEncoder.encode("curly"));
       curly.setAuthorities(Arrays.asList("ADMIN"));
       accountRepo.save(curly);
        
    }
    
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/books";
    }
}
