package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.figures.Pentagon;

@Service
public class PentagonMapper implements Converter<CreateShapeCommand, Pentagon> {

    @Override
    public Pentagon convert(MappingContext<CreateShapeCommand, Pentagon> mappingContext) {
        CreateShapeCommand command = mappingContext.getSource();

        return Pentagon.builder()
                .createdDate(command.getDate())
                .side(command.getParameter().getSide())
                .createdBy(command.getUser())
                .build();
    }
}