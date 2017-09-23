/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;

/**
 *
 * @author veke
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private BookService bookService;

    public List<Author> getAuthors() {
        return authorRepo.findAll();

    }

    public void save(String name) {
        Author author = new Author();
        author.setName(name);
        authorRepo.save(author);
    }

    public Author getAuthor(String name) {
        return authorRepo.findByName(name);
    }

    public Author getAuthor(Long id) {
        return authorRepo.findOne(id);
    }
    
    public void delete (Long id){
        Author author = getAuthor(id);
        if (author.getBooks() != null){
           for (Book book : author.getBooks()){
               book.getAuthors().remove(author);
           }    
        }
        authorRepo.delete(id);
    }
    
    
    @Transactional
    public void addAuthorToBook(String bookName,String authorName) {
            Author author = authorRepo.findByName(authorName);
            Book book = bookService.getBook(bookName);
            author.getBooks().add(book);
            book.getAuthors().add(author);     
            bookRepo.save(book);
    }

    public void addAuthorToBook(Long bookId,Long authorId) {
            Author author = authorRepo.findOne(authorId);
            Book book = bookService.getBook(bookId);
            author.getBooks().add(book);
            book.getAuthors().add(author);
            bookRepo.save(book);
            
    }

}
