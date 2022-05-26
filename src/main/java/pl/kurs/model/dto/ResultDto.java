package pl.kurs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto extends RepresentationModel<ResultDto> {

    private int id;
    private String type;
    private int version;
    private String createdBy;
    private String createdDate;
    private String parameters;
    private double area;


}
