package Dao;

import java.util.List;

import model.Book;

public interface BookDao {
	
	Book findById(int id);
    List<Book> findAll();
    int save(Book book);
    int update(Book book);
    int deleteById(int id);

}
