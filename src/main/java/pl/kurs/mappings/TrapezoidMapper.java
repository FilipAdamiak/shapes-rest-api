package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Trapezoid;

@Service
public class TrapezoidMapper implements Converter<CreateShapeCommand, Trapezoid> {

    @Override
    public Trapezoid convert(MappingContext<CreateShapeCommand, Trapezoid> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Trapezoid.builder()
                .createdDate(command.getDate())
                .height(command.getParameter().getHeight())
                .bottomBase(command.getParameter().getBottomBase())
                .upperBase(command.getParameter().getUpperBase())
                .createdBy(command.getUser())
                .build();
    }
}