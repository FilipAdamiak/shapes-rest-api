package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Circle;

@Service
public class CircleMapper implements Converter<CreateShapeCommand, Circle> {

    @Override
    public Circle convert(MappingContext<CreateShapeCommand, Circle> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Circle.builder()
                .createdDate(command.getDate())
                .radius(command.getParameter().getRadius())
                .createdBy(command.getUser())
                .build();
    }

}
