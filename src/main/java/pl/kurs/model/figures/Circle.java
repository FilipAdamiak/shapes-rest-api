package pl.kurs.model.figures;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.enums.Type;
import pl.kurs.model.searchcriteria.Parameters;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Circle extends Shape {

    private Double radius;

    @Builder
    public Circle(LocalDateTime createdDate, User createdBy,  Double radius) {
        super(createdDate, createdBy);
        this.radius = radius;
    }

    public Circle() {
    }

    @Override
    public Double getArea() {
        return Math.PI*Math.pow(radius, 2);
    }

    @Override
    public String getType() {
        return Type.CIRCLE.label;
    }

    @Override
    public String getParameters() {
        return "radius: " + radius;
    }

    @Override
    public void setParameters(Parameters parameters) {
        this.radius = parameters.getRadius();
    }

}
