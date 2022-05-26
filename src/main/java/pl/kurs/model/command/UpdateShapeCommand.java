package pl.kurs.model.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.model.User;
import pl.kurs.model.searchcriteria.Parameters;
import pl.kurs.validation.annotation.UniqueUser;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdateShapeCommand {

    @UniqueUser
    private User user;
    private LocalDateTime date;
    @NotNull(message = "PARAMETERS_NOT_NULL")
    private Parameters parameter;
    @NotNull(message = "VERSION_NOT_EMPTY")
    private Integer version;

}
