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
public class Trapezoid extends Shape {

    private Double bottomBase;
    private Double height;
    private Double upperBase;

    @Builder
    public Trapezoid(LocalDateTime createdDate, User createdBy, Double bottomBase, Double height, Double upperBase) {
        super(createdDate, createdBy);
        this.bottomBase = bottomBase;
        this.height = height;
        this.upperBase = upperBase;
    }


    public Trapezoid(){}

    @Override
    public Double getArea() {
        return ((bottomBase + upperBase) * height) / 2;
    }

    @Override
    public String getType() {
        return Type.TRAPEZOID.label;
    }

    @Override
    public String getParameters() {
        return "bottomBase: " + bottomBase + " " +
                "upperBase: " + upperBase + " " +
                "height: " + height;
    }

    @Override
    public void setParameters(Parameters parameters) {
        this.bottomBase = parameters.getBottomBase();
        this.height = parameters.getHeight();
        this.upperBase = parameters.getUpperBase();
    }
}
