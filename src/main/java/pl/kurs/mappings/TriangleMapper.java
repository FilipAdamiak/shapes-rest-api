package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Triangle;

@Service
public class TriangleMapper implements Converter<CreateShapeCommand, Triangle> {

    @Override
    public Triangle convert(MappingContext<CreateShapeCommand, Triangle> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Triangle.builder()
                .createdDate(command.getDate())
                .base(command.getParameter().getBase())
                .height(command.getParameter().getHeight())
                .createdBy(command.getUser())
                .build();
    }

}
