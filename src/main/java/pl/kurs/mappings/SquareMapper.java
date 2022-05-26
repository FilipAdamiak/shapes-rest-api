package pl.kurs.mappings;


import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Square;

@Service
public class SquareMapper implements Converter<CreateShapeCommand, Square> {

    @Override
    public Square convert(MappingContext<CreateShapeCommand, Square> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Square.builder()
                .createdDate(command.getDate())
                .side(command.getParameter().getSide())
                .createdBy(command.getUser())
                .build();
    }
}