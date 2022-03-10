package co.edu.udea.unittestcase.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessageEntity {

    private Object body;
    private String message;

}
