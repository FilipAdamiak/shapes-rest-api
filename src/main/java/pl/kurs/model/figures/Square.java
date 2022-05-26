package pl.kurs.model.figures;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.enums.Type;
import pl.kurs.model.searchcriteria.Parameters;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Component
public class Square extends Shape {

    private Double side;

    @Builder
    public Square(LocalDateTime createdDate, User createdBy, Double side) {
        super(createdDate, createdBy);
        this.side = side;
    }

    public Square(){}

    @Override
    public Double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public String getType() {
        return Type.SQUARE.label;
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
