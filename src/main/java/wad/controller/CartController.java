/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Book;
import wad.domain.ShoppingCart;
import wad.repository.BookRepository;
import wad.service.BookService;

/**
 *
 * @author veke
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private BookRepository bookRepo;  
       
    @Autowired
    private ShoppingCart shoppingCart;
    
    @Autowired
    private BookService bookService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("books", shoppingCart.getBooks());
        model.addAttribute("sum", shoppingCart.getPrice());
        return "cart";
    }
    
    
    @Transactional
    @RequestMapping (value = "/books/{id}", method = RequestMethod.POST)
    public String post (@PathVariable (value = "id")Long id){
        Book book = bookService.getBook(id);
        shoppingCart.addToCart(book);
        return "redirect:/cart";
        
    }
    
    @Transactional
    @RequestMapping (value = "/books/{id}", method = RequestMethod.DELETE)
    public String delete (@PathVariable (value = "id")Long id){
        Book book = bookService.getBook(id);
        shoppingCart.removeFromCart(book);        
        return "redirect:/cart";
    }
    
}
