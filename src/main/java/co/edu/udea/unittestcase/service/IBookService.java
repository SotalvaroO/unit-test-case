package co.edu.udea.unittestcase.service;

import co.edu.udea.unittestcase.model.BookEntity;

import java.util.List;


public interface IBookService {

    public List<BookEntity> findAllBooks();
    public BookEntity findBookById(Long id);
    public BookEntity findBookByIsbn(String isbn);
    public BookEntity createBook(BookEntity book);
    public BookEntity updateBook(BookEntity book);
    public BookEntity updateStock(int stock, Long id);
    public BookEntity deleteBookById(Long id);

}
