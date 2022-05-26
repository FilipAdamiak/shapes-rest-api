package pl.kurs.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Type {

    CIRCLE("circle"),
    PENTAGON("pentagon"),
    RECTANGLE("rectangle"),
    SQUARE("square"),
    TRAPEZOID("trapezoid"),
    TRIANGLE("triangle");

    public final String label;
    Type(String label) {
        this.label = label;
    }

    public static List<String> getLabels() {
        List<String> result = new ArrayList<>();
        for(Type t : Type.values()) {
            result.add(t.label);
        }
        return result;
    }


}
