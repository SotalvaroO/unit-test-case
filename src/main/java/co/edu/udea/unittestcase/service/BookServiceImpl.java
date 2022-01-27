package co.edu.udea.unittestcase.service;

import co.edu.udea.unittestcase.model.BookEntity;
import co.edu.udea.unittestcase.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {


    private final IBookRepository _bookRepository;

    @Override
    public List<BookEntity> findAllBooks() {
        return _bookRepository.findAll();
    }

    @Override
    public BookEntity findBookById(Long id) {
        return _bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookEntity findBookByIsbn(String isbn) {
        return _bookRepository.findByIsbn(isbn).orElse(null);
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        BookEntity bookDB = findBookByIsbn(book.getIsbn());
        if (bookDB != null) {
            return null;
        }
        return _bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(BookEntity book) {
        BookEntity bookDB = findBookById(book.getId());
        if (bookDB == null){
            return null;
        }
        bookDB.setName(book.getName());
        bookDB.setIsbn(book.getIsbn());
        bookDB.setSynopsis(book.getSynopsis());
        bookDB.setAuthor(book.getAuthor());
        return _bookRepository.save(bookDB);
    }

    @Override
    public BookEntity updateStock(int stock, Long id) {
        BookEntity book = findBookById(id);

        if (book == null){
            return null;
        }
        int totalStock = book.getStock() + stock;
        book.setStock(totalStock);
        return _bookRepository.save(book);
    }

    @Override
    public BookEntity deleteBookById(Long id) {
        BookEntity book = findBookById(id);
        if (book == null){
            return null;
        }
        _bookRepository.deleteById(id);
        return book;
    }
}
