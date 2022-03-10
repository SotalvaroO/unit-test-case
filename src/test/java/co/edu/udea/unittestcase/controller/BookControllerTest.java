package co.edu.udea.unittestcase.controller;

import co.edu.udea.unittestcase.model.BookEntity;
import co.edu.udea.unittestcase.model.ResponseMessageEntity;
import co.edu.udea.unittestcase.service.IBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IBookService _bookService;

    BookEntity book;

    ResponseMessageEntity response;

    ObjectMapper om;

    @BeforeEach
    void setUp() {

        book = BookEntity.builder()
                .id(1L)
                .isbn("9786124262883")
                .name("Agua Para Chocolate")
                .synopsis("Obscenidades encima de un caballo")
                .author("Laura Esquivel")
                .stock(6)
                .build();

        response = ResponseMessageEntity.builder()
                .body(Arrays.asList(book))
                .build();

        om = new ObjectMapper();

    }

    @Test
    void findAllBooks() throws Exception {


        mvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        when(_bookService.findAllBooks()).thenReturn(Arrays.asList(book));

        mvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void findBookById() throws Exception {
        mvc.perform(get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        when(_bookService.findBookById(1L)).thenReturn(book);
        mvc.perform(get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void createBook() throws Exception {
        String jsonRequest = om.writeValueAsString(book);

        when(_bookService.createBook(book))
               .thenReturn(book);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isCreated());

        when(_bookService.findBookByIsbn(book.getIsbn())).thenReturn(book);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBook() throws Exception {

        String jsonRequest = om.writeValueAsString(book);

        mvc.perform(put("/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isNotFound());

        when(_bookService.findBookById(1L)).thenReturn(book);

        mvc.perform(put("/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());



    }

    @Test
    void updateStock() throws Exception {

        mvc.perform(get("/book/1/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        when(_bookService.findBookById(1L)).thenReturn(book);
        mvc.perform(get("/book/1/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deleteBookById() throws Exception {
        mvc.perform(delete("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        when(_bookService.findBookById(1L)).thenReturn(book);
        mvc.perform(delete("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}