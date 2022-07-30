package pl.kurs.model.figures;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.enums.Type;
import pl.kurs.model.searchcriteria.Parameters;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pentagon extends Shape {

    private Double side;

    @Builder
    public Pentagon(LocalDateTime createdDate, User createdBy,Double side) {
        super(createdDate, createdBy);
        this.side = side;
    }

    public Pentagon(){}

    @Override
    public Double getArea() {
        return (Math.sqrt(5 * (5 + 2
                * (Math.sqrt(5)))) * side * side) / 4;
    }

    @Override
    public String getType() {
        return Type.PENTAGON.label;
    }

    @Override
    public String getParameters() {
        return "side: " + side;
    }

    @Override
    public void setParameters(Parameters parameters) {
        this.side = parameters.getSide();
    }
}
