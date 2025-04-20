package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Dao.BookDao;
import model.Book;


@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookDao bookDao;

	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookDao.findById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK); // Found, return 200 OK with book
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found, return 404 Not Found
        }
    }
	
	@GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookDao.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK); // Return 200 OK with all books
    }
	
	@PostMapping
    public ResponseEntity<Void> createBook(@RequestBody Book book) {
        int result = bookDao.save(book);
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED); // Created successfully, return 201 Created
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error during creation, return 500
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        int result = bookDao.update(book);
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Updated successfully, return 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Book not found for update, return 404
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        int result = bookDao.deleteById(id);
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Deleted successfully, return 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Book not found for deletion, return 404
        }
    }
}
