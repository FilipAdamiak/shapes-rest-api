package pl.kurs.model.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kurs.model.Shape;
import pl.kurs.repository.repointerface.ShapeInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RepoFactory<T extends Shape> {
    private Map<String, ShapeInterface<T>> strategies;

    @Autowired
    public RepoFactory(Set<ShapeInterface<T>> shapes) {
        createStrategy(shapes);
    }

    public ShapeInterface<T> findStrategy(String type) {
        return strategies.get(type);
    }

    private void createStrategy(Set<ShapeInterface<T>> shapes) {
        strategies = new HashMap<>();
        shapes.forEach(
                mapToPredicate -> strategies.put(mapToPredicate.getType(),
                        mapToPredicate)
        );
    }
}
