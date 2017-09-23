/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;

/**
 *
 * @author veke
 */


@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    
    @Before
    public void setup() {
        authorService.save("TestAuthor");
        genreService.save("TestGenre");
        bookService.save("TestBook", 50.00, "TestAuthor", "TestGenre");
    }

    @Test
    public void shouldFindOneAuthor() {
        List<Author> authors = authorService.getAuthors();
        Assert.assertNotNull("Should not be null - Authors ", authors);
        Assert.assertEquals("Should contain 1 - Author", 1, authors.size());
    }

    @Test
    public void shouldFindOneGenre() {
        List<Genre> genres = genreService.getGenres();
        Assert.assertNotNull("Should not be null - Genres", genres);
        Assert.assertEquals("Should contain 1 - Genre", 1, genres.size());
    }

    @Test
    public void shouldFindOneBook() {
        List<Book> books = bookService.getBooks();
        Assert.assertNotNull("Should not be null - Book", books);
        Assert.assertEquals("Should contain 1 - Book", 1, books.size());
    }

    @Test
    public void bookShouldBeSavedWithCorrectParams() {
        List<Book> books = bookService.getBooks();
        Book book = books.get(0);
        String bookName = "TestBook";
        Double bookValue = 50.00;
        Genre genre = genreService.getGenre("TestGenre");
        Author author = authorService.getAuthor("TestAuthor");
        Assert.assertEquals("Should contain right Name - Book", book.getName(), bookName);
        Assert.assertEquals("Should contain right Price - Book", book.getPrice(), bookValue);
        Assert.assertEquals("Should contain right Genre - Book", book.getGenre(), genre);
        Assert.assertEquals("Should contain right Author - Book", book.getAuthors().get(0), author);
    }

    @Test
    public void shouldBeAbleToRemoveGenres() {
        List<Genre> genres = genreService.getGenres();
        genreService.delete(genres.get(0).getId());
        Assert.assertEquals("Genre list should be empty", 0, genres.size());
    }

    @Test
    public void shouldBeAbleToRemoveAuthors() {
        List<Author> authors = authorService.getAuthors();
        authorService.delete(authors.get(0).getId());
        Assert.assertEquals("Author list should be empty", 0, authors.size());
    }

    @Test
    public void shouldBeAbleToRemoveBooks() {
        List<Book> books = bookService.getBooks();
        bookService.delete(books.get(0).getId());
        Assert.assertEquals("Author list should be empty", 0, books.size());
    }

}
