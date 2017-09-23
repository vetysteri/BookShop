/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Book;
import wad.repository.BookRepository;
import wad.service.AuthorService;
import wad.service.BookService;
import wad.service.GenreService;

/**
 *
 * @author veke
 */
@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private AuthorService authorService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String home (Model model){
        model.addAttribute("books",bookService.getBooks());
        model.addAttribute("genres",genreService.getGenres());
        model.addAttribute("authors",authorService.getAuthors());
        return "books";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String saveBook(@RequestParam String name, @RequestParam Double price,@RequestParam String authorName, @RequestParam String genreName){
        bookService.save(name, price,authorName, genreName);
        return "redirect:/books";
        
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getSingleBook(@PathVariable (value="id") Long bookId, Model model){
        model.addAttribute("book", bookService.getBook(bookId));
        model.addAttribute("genres",genreService.getGenres());
        model.addAttribute("authors",authorService.getAuthors());
        return "book";
    }
    
    @RequestMapping(value ="{id}", method = RequestMethod.POST)
    public String addToBook (@PathVariable (value="id") Long bookId, @RequestParam (value="genreId", required = false )Long genreId
    ,@RequestParam (value="authorId", required=false) Long authorId){   
        Book book = bookService.getBook(bookId);
        if (book.getAuthors().isEmpty()){
        authorService.addAuthorToBook(bookId, authorId);     
        }
        if (book.getGenre() == null){ 
        genreService.addGenreToBook(bookId, genreId);         
        }
       return "redirect:/books/" + bookId;
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable (value="id") Long bookId){
        System.out.println("bookid :" + bookId);
        bookService.delete(bookId);
        return "redirect:/books";
        
    }

    
}
