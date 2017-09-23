/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wad.domain.Account;
import wad.repository.AccountRepository;


/**
 *
 * @author veke
 */
@Service
public class InitService {
    

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepo;
     @Autowired
     private GenreService genreService;
     
     @Autowired
     private AuthorService authorService;
     
     @Autowired 
     private BookService bookService;
   
        
    @PostConstruct
    public void initGenres(){
     genreService.save("Horror");
     genreService.save("Fantasy");
     genreService.save("Autobiography");
     genreService.save("Self-Dev");
     genreService.save("Thriller");
     genreService.save("Conspiracy");
    }
    
    
    @PostConstruct
    public void initAuthors(){
    authorService.save("Robert Greene");
    authorService.save("George R.R Martin");
    authorService.save("Eckhart Tolle");
    authorService.save("Richard Branson");
    authorService.save("Stephen King");
    authorService.save("David Icke");
        initBooks();
        
    }

    public void initBooks(){
        bookService.save("Biggest Secret", 25.00,"David Icke","Conspiracy");
        bookService.save("Phantom Self", 20.00,"David Icke","Conspiracy");
        bookService.save("Children of The Matrix", 23.99,"David Icke","Conspiracy");
        bookService.save("It", 25.00,"Stephen King","Horror");
        bookService.save("Red Dot", 22.95,"Stephen King","Horror");
        bookService.save("Cell", 19.95,"Stephen King","Horror");
        
    }
    
    @PostConstruct
    public void init() {

    }
    
}
