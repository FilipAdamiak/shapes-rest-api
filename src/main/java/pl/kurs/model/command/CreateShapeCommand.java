package pl.kurs.model.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import pl.kurs.model.User;
import pl.kurs.model.searchcriteria.Parameters;
import pl.kurs.validation.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@Builder
@CreateShape
public class CreateShapeCommand {

    @OneOfShapeTypes
    private String type;
    @UniqueUser
    private User user;
    @FutureOrPresent(message = "DATE_FROM_THE_PAST")
    private LocalDateTime date;
    @NotNull(message = "PARAMETERS_NOT_NULL")
    private Parameters parameter;

}
