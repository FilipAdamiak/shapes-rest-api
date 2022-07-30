package pl.kurs.mappings;


import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.controller.UserController;
import pl.kurs.model.Shape;
import pl.kurs.model.dto.ResultDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class EntityToDtoMapper implements Converter<Shape, ResultDto>{

    @Override
    public ResultDto convert(MappingContext<Shape, ResultDto> mappingContext) {
        Shape entity = mappingContext.getSource();

        ResultDto result = ResultDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .area(entity.getArea())
                .createdBy(entity.getCreatedBy().toString())
                .createdDate(entity.getCreatedDate().toString())
                .version(entity.getVersion())
                .parameters(entity.getParameters())
                .build();

        result.add(linkTo(methodOn(UserController.class).getSingleUser(entity.getCreatedBy().getId())).withRel("shape-owner"));
        return result;
    }

}
