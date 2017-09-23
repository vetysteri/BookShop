/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.repository.AuthorRepository;
import wad.service.AuthorService;

/**
 *
 * @author veke
 */

@Controller
@RequestMapping("/authors")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AuthorController {
    
    @Autowired
    private AuthorRepository authorRepo;
    
    @Autowired
    private AuthorService authorService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("authors",authorService.getAuthors());
        return "authors";
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam String name){
        authorService.save(name);
        return "redirect:/authors";
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String deleteAuthor(@PathVariable (value="id") Long id){
        authorService.delete(id);
        return "redirect:/authors";
    }
    
    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public String getBooksByAuthor(@PathVariable (value="id") Long authorId, Model model){
        model.addAttribute("author", authorService.getAuthor(authorId));
        return "author";
    }
    
}
