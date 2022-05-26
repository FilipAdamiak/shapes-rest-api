package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Rectangle;

@Service
public class RectangleMapper implements Converter<CreateShapeCommand, Rectangle> {

    @Override
    public Rectangle convert(MappingContext<CreateShapeCommand, Rectangle> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Rectangle.builder()
                .createdDate(command.getDate())
                .width(command.getParameter().getWidth())
                .height(command.getParameter().getHeight())
                .createdBy(command.getUser())
                .build();
    }
}
