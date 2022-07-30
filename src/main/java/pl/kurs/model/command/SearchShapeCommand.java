package pl.kurs.model.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.model.searchcriteria.SearchParameters;
import pl.kurs.validation.annotation.MatchingParameters;
import pl.kurs.validation.annotation.OneOfShapeTypes;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@MatchingParameters
public class SearchShapeCommand {

    @OneOfShapeTypes
    private String type;
    @NotNull(message = "PARAMETERS_NOT_NULL")
    private SearchParameters parameter;

}
