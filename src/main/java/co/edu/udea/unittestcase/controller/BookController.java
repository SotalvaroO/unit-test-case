package co.edu.udea.unittestcase.controller;

import co.edu.udea.unittestcase.model.BookEntity;
import co.edu.udea.unittestcase.model.ResponseMessageEntity;
import co.edu.udea.unittestcase.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private IBookService _bookService;

    @GetMapping
    public ResponseEntity<ResponseMessageEntity> findAllBooks(){
        ResponseMessageEntity response = new ResponseMessageEntity();
        List<BookEntity> books = _bookService.findAllBooks();
        if (books.isEmpty()){
            response.setMessage("No se encontraron libros");
            response.setBody(books);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        response.setMessage("Éxito!");
        response.setBody(books);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseMessageEntity> findBookById(@PathVariable Long id){
        ResponseMessageEntity response = new ResponseMessageEntity();
        BookEntity book = _bookService.findBookById(id);
        if (book == null) {
            response.setMessage("El libro no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setMessage("Obtenido con éxito");
        response.setBody(book);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseMessageEntity> createBook(@RequestBody BookEntity book) {
        ResponseMessageEntity response = new ResponseMessageEntity();
        BookEntity bookDB = _bookService.findBookByIsbn(book.getIsbn());
        if (bookDB != null) {
            response.setMessage("El libro ya existe");
            response.setBody(bookDB);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setMessage("Creado con éxito!");
        response.setBody(_bookService.createBook(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseMessageEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity book) {
        book.setId(id);
        ResponseMessageEntity response = new ResponseMessageEntity();
        BookEntity bookDB = _bookService.findBookById(id);
        if (bookDB == null) {
            response.setMessage("El libro no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setMessage("Modificado con éxito");
        response.setBody(bookDB);
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/{id}/{stock}")
    public ResponseEntity<ResponseMessageEntity> updateStock(@PathVariable Long id, @PathVariable int stock) {
        ResponseMessageEntity response = new ResponseMessageEntity();
        BookEntity book = _bookService.findBookById(id);
        if (book == null) {
            response.setMessage("El libro no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        book = _bookService.updateStock(stock, id);
        response.setMessage("Modificado con éxito");
        response.setBody(book);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseMessageEntity> deleteBookById(@PathVariable Long id){
        ResponseMessageEntity response = new ResponseMessageEntity();
        BookEntity book = _bookService.findBookById(id);
        if (book == null) {
            response.setMessage("El libro no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        book = _bookService.deleteBookById(id);
        response.setMessage("Borrado con éxito");
        response.setBody(book);
        return ResponseEntity.ok(response);
    }

}
