/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wad.AbstractTest;
import wad.domain.Author;
import wad.domain.Book;
import wad.domain.Genre;
import wad.repository.AuthorRepository;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;

/**
 *
 * @author veke
 */


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTest extends AbstractTest {

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Test
    public void shouldFindAuthors() {
        List<Author> authors = authorService.getAuthors();
        Assert.assertNotNull("Should not be null - Authors ", authors);
        Assert.assertEquals("Should contain 1 - Author", 6, authors.size());
    }

    @Test
    public void shouldFindGenres() {
        List<Genre> genres = genreService.getGenres();
        Assert.assertNotNull("Should not be null - Genres", genres);
        Assert.assertEquals("Should contain 1 - Genre", 6, genres.size());
    }

    @Test
    public void shouldFindBookS() {
        List<Book> books = bookService.getBooks();
        Assert.assertNotNull("Should not be null - Book", books);
        Assert.assertEquals("Should contain 1 - Book", 6, books.size());
    }

    @Test
    public void bookShouldBeSavedWithCorrectParams() {
        List<Book> books = bookService.getBooks();
        Book book = books.get(0);
        String bookName = "Biggest Secret";
        Double bookValue = 25.00;
        Genre genre = genreService.getGenre("Conspiracy");
        Author author = authorService.getAuthor("David Icke");
        Assert.assertEquals("Should contain right Name - Book", book.getName(), bookName);
        Assert.assertEquals("Should contain right Price - Book", book.getPrice(), bookValue);
        Assert.assertEquals("Should contain right Genre - Book", book.getGenre(), genre);
        Assert.assertEquals("Should contain right Author - Book", book.getAuthors().get(0), author);
    }

}
