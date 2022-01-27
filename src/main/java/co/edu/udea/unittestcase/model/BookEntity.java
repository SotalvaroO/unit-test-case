package co.edu.udea.unittestcase.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_books")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;

    private String name;

    private String synopsis;

    private String author;

    private int stock;

}
