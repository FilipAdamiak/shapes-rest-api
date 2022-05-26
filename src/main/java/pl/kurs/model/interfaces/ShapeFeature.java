package pl.kurs.model.interfaces;

import pl.kurs.model.searchcriteria.Parameters;

public interface ShapeFeature {
    Double getArea();
    String getParameters();
    void setParameters(Parameters parameters);
    String getType();
}
