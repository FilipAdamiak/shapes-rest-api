package pl.kurs.model.interfaces;

import com.querydsl.core.types.Predicate;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.searchcriteria.Parameters;

public interface ShapeFeature {
    Double getArea();
    String getParameters();
    void setParameters(Parameters parameters);
    String getType();
}
