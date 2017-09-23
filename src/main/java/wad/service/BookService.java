package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;
import wad.repository.BookRepository;


/**
 *
 * @author veke
 */
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;
    
     @Autowired
     private GenreService genreService;
     
     @Autowired
     private AuthorService authorService;
    
    
    public List <Book> getBooks(){        
        return bookRepo.findAll();
    }
    
    public Book getBook(Long id){
        return bookRepo.findOne(id);
    }
    public Book getBook(String bookName){
        return bookRepo.findByName(bookName);
    }
    
    public void delete(Long id){
     Book book = getBook(id);
     Genre genre = book.getGenre();
     genre.getBooks().remove(book);
     
     if (book.getAuthors() != null){
     for (Author author : book.getAuthors()){
         author.getBooks().remove(book);
     }
     }
     bookRepo.delete(book);
    }
    
    
    public void save(String bookName, Double price, String authorName, String genreName){
        Book book = new Book();
        book.setName(bookName);
        book.setPrice(price);
        bookRepo.save(book);
        authorService.addAuthorToBook(bookName, authorName);
        genreService.addGenreToBook(bookName, genreName);
    }
    
    

}
