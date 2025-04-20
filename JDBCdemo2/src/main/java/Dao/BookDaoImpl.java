package Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Book;

@Repository
public class BookDaoImpl implements BookDao{
	
	private final JdbcTemplate template;
	
	@Autowired
	public BookDaoImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Book findById(int id) {
		try {
            return template.queryForObject("SELECT id, title, author, isbn FROM books WHERE id = ?",
                new BeanPropertyRowMapper<>(Book.class),id);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no book is found
        }
	}

	@Override
	public List<Book> findAll() {
		return template.query(
	            "SELECT id, title, author, isbn FROM books",
	            new BeanPropertyRowMapper<>(Book.class)
	        );
	}

	@Override
	public int save(Book book) {
		return template.update(
	            "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)",
	            book.getTitle(), book.getAuthor(), book.getIsbn()
	        );
	}

	@Override
	public int update(Book book) {
		return template.update(
	            "UPDATE books SET title = ?, author = ?, isbn = ? WHERE id = ?",
	            book.getTitle(), book.getAuthor(), book.getIsbn(), book.getId()
	        );
	}

	@Override
	public int deleteById(int id) {
		return template.update(
	            "DELETE FROM books WHERE id = ?",
	            id
	        );
	}

}
