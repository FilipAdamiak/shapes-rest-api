package pl.kurs.model.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kurs.model.Shape;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TypesFactory {
    private Map<String, ShapeInterface<? extends Shape>> strategies;

    @Autowired
    public TypesFactory(Set<ShapeInterface<? extends Shape>> shapes) {
        createStrategy(shapes);
    }

    public ShapeInterface<? extends Shape> findStrategy(String type) {
        return strategies.get(type);
    }

    private void createStrategy(Set<ShapeInterface<? extends Shape>> shapes) {
        strategies = new HashMap<>();
        shapes.forEach(
                mapToPredicate -> strategies.put(mapToPredicate.getType(),
                        mapToPredicate)
        );
    }

}
