package co.edu.udea.unittestcase.repository;

import co.edu.udea.unittestcase.model.BookEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IBookRepositoryTest {

    @Autowired
    private IBookRepository _bookRepository;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        book = BookEntity.builder()
                .id(1L)
                .isbn("9786124262883")
                .name("Como agua Para Chocolate")
                .synopsis("Descripcion del libro")
                .author("Laura Esquivel")
                .stock(6)
                .build();

        _bookRepository.save(book);
        //Assertions.assertThat(book.getId()).isGreaterThan(0);
    }

    @Test
    void findByIsbn() {
        assertNotNull(_bookRepository.findByIsbn("9786124262883"));
    }

}