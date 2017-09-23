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
import wad.domain.Book;
import wad.domain.Genre;
import wad.repository.BookRepository;
import wad.repository.GenreRepository;

/**
 *
 * @author veke
 */
@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    public List<Genre> getGenres() {
        return genreRepo.findAll();
    }

    @Transactional
    public void save(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepo.save(genre);
    }

    public Genre getGenre(String name) {
        return genreRepo.findByName(name);
    }

    public Genre getGenre(Long id) {
        return genreRepo.findOne(id);
    }

    @Transactional
    public void delete(Long id) {
        genreRepo.delete(id);
    }

    @Transactional
    public void deleteGenre(Long id) {
        Genre genre = getGenre(id);
        if (genre.getBooks() != null) {
            for (Book book : genre.getBooks()) {
                book.setGenre(null);
            }
        }
        delete(id);
    }

    @Transactional
    public void addGenreToBook(Long bookId, String genreName) {
        Book book = bookService.getBook(bookId);
        Genre genre = getGenre(genreName);
        book.setGenre(genre);
    }

    @Transactional
    public void addGenreToBook(Long bookId, Long genreId) {
        Book book = bookService.getBook(bookId);
        Genre genre = getGenre(genreId);
        book.setGenre(genre);
        genre.getBooks().add(book);
    }

    @Transactional
    public void addGenreToBook(String bookName, String genreName) {
        Book book = bookService.getBook(bookName);
        Genre genre = getGenre(genreName);
        book.setGenre(genre);
        genre.getBooks().add(book);
    }

}
