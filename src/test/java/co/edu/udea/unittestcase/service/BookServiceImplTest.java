package co.edu.udea.unittestcase.service;

import co.edu.udea.unittestcase.model.BookEntity;
import co.edu.udea.unittestcase.repository.IBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private IBookRepository _bookRepository;

    private IBookService _bookService;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this._bookService = new BookServiceImpl(_bookRepository);

        book = BookEntity.builder()
                .id(1L)
                .isbn("9786124262883")
                .name("Agua Para Chocolate")
                .synopsis("Obscenidades encima de un caballo")
                .author("Laura Esquivel")
                .stock(6)
                .build();





    }

    @Test
    void findAllBooks() {
        when(_bookRepository.findAll())
                .thenReturn(Arrays.asList(book));
        List<BookEntity> books = _bookService.findAllBooks();
        Assertions.assertThat(books).isNotEmpty();
    }

    @Test
    void findBookById() {
        when(_bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        BookEntity found = _bookService.findBookById(1L);
        Assertions.assertThat(found.getIsbn()).isEqualTo("9786124262883");
    }

    @Test
    void findBookByIsbn() {
        when(_bookRepository.findByIsbn("9786124262883"))
                .thenReturn(Optional.of(book));
        BookEntity found = _bookService.findBookByIsbn("9786124262883");
        Assertions.assertThat(found.getId()).isEqualTo(1L);
        Assertions.assertThat(found).isNotNull();
    }

    @Test
    void createBook() {
        when(_bookRepository.save(book)).thenReturn(book);
        assertNotNull(_bookService.createBook(book));
    }

    @Test
    void updateBook() {
        when(_bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        when(_bookRepository.save(book)).thenReturn(book);
        assertNotNull(_bookService.updateBook(book));
    }

    @Test
    void updateStock() {
        when(_bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        when(_bookRepository.save(book)).thenReturn(book);
        BookEntity updatedBook = _bookService.updateStock(14, 1L);
        Assertions.assertThat(updatedBook.getStock()).isEqualTo(20);
    }

    @Test
    void deleteBookById() {
        when(_bookRepository.findById(1L))
                .thenReturn(Optional.of(book));
        assertNotNull(_bookService.deleteBookById(1L));
    }
}