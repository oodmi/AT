package controller;

import controller.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.Book;
import service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean insertBook(@RequestBody Book book) {
        return bookService.insertBook(book);
    }

    @RequestMapping(value = "/{isn}", method = RequestMethod.PUT)
    public boolean updateBook(@PathVariable("isn") Long isn, @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @RequestMapping(value = "/{isn}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable("isn") Long isn) {
        return bookService.deleteBook(isn);
    }

    @RequestMapping(value = "/limit/{offset}/{count}", method = RequestMethod.GET)
    public List<BookResponse> getBooks(@PathVariable("offset") Long offset, @PathVariable("count") Long count) {
        return bookService.getBooks(offset, count);
    }

    @RequestMapping(value = "/take/{isn}", method = RequestMethod.PUT)
    public void takeBook(@PathVariable("isn") Long isn) {
        bookService.takeBook(isn);
    }

    @RequestMapping(value = "/return/{isn}", method = RequestMethod.PUT)
    public void returnBook(@PathVariable("isn") Long isn) {
        bookService.returnBook(isn);
    }
}
