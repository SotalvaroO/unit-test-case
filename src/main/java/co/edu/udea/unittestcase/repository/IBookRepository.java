package co.edu.udea.unittestcase.repository;

import co.edu.udea.unittestcase.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {

    public Optional<BookEntity> findByIsbn(String isbn);

//    @Transactional
//    @Modifying
//    @Query("UPDATE BookEntity b SET b.stock = ?1 WHERE b.id = ?2")
//    public void updateStockById(int stock, Long id);

}
