package pl.kurs.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
public class UserDto extends RepresentationModel<UserDto> {

    private int id;
    private String name;
    private String surname;
    private int shapesCount;
    private boolean deleted;
    private int version;
    private String email;
}
