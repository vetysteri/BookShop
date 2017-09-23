package wad.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import wad.domain.Book;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private Map<Book, Long> books;

    public ShoppingCart() {
        this.books = new TreeMap<>();
    }

    public Map<Book, Long> getBooks() {
        return books;
    }

    public void setBooks(Map<Book, Long> books) {
        this.books = books;
    }

    public void addToCart(Book book) {
        if (this.books.containsKey(book)) {
            this.books.put(book, this.books.get(book) + 1);
        } else {
            this.books.put(book, (long) 1);
        }

    }

    public void removeFromCart(Book book) {
        if (this.books.get(book) != null) {
            long value = this.books.get(book);
            value--;
            if (value < 1) {
                this.books.remove(book);
            } else {
                this.books.put(book, value);
            }
        }

    }

    public double getPrice() {
        double price = 0;
        for (Book book : books.keySet()) {
            price += book.getPrice() * books.get(book);
        }
        return price;
    }

    public void clearCart() {
        this.books.clear();

    }

}
