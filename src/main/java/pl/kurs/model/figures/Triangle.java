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
public class Triangle extends Shape {

    private Double base;
    private Double height;

    @Builder
    public Triangle(LocalDateTime createdDate, User createdBy, Double base, Double height) {
        super(createdDate, createdBy);
        this.base = base;
        this.height = height;
    }

    public Triangle(){}

    @Override
    public Double getArea() {
        return (base * height) / 2;
    }

    @Override
    public String getType() {
        return Type.TRIANGLE.label;
    }

    @Override
    public String getParameters() {
        return "base: " + base + " " +
                "height: " + height;
    }

    @Override
    public void setParameters(Parameters parameters) {
        this.base = parameters.getBottomBase();
        this.height = parameters.getHeight();
    }
}
