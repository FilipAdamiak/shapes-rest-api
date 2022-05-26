package pl.kurs.model.constants;

import pl.kurs.model.Shape;
import pl.kurs.model.figures.*;

import java.lang.instrument.ClassDefinition;
import java.util.HashMap;
import java.util.Map;

public class MapOfClasses {

    public final static Map<String, Class> MAP = new HashMap<>() {
        {
            put("circle", Circle.class);
            put("rectangle", Rectangle.class);
            put("triangle", Triangle.class);
            put("square", Square.class);
            put("trapezoid", Trapezoid.class);
            put("pentagon", Pentagon.class);
        }
    };

}
