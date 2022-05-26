package pl.kurs.model.figures;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.enums.Type;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.searchcriteria.Parameters;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@Component
public class Rectangle extends Shape  {

    private Double width;
    private Double height;

    @Builder
    public Rectangle(LocalDateTime createdDate, User createdBy, Double width, Double height) {
        super(createdDate, createdBy);
        this.width = width;
        this.height = height;
    }
    public Rectangle() {

    }

    @Override
    public Double getArea() {
        return width * height;
    }

    @Override
    public String getType() {
        return Type.RECTANGLE.label;
    }

    @Override
    public String getParameters() {
        return "width: " + width + " " +
                "height: " + height;
    }

    @Override
    public void setParameters(Parameters parameters) {
        this.height = parameters.getHeight();
        this.width = parameters.getWidth();
    }
}
