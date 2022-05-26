package pl.kurs.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.controller.UserController;
import pl.kurs.model.User;
import pl.kurs.model.dto.UserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class UserToDtoMapper implements Converter<User, UserDto> {


    @Override
    public UserDto convert(MappingContext<User, UserDto> mappingContext) {
        User user = mappingContext.getSource();

        UserDto userDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .id(user.getId())
                .deleted(user.isDeleted())
                .version(user.getVersion())
                .shapesCount(user.getShapes().size())
                .email(user.getEmail())
                .build();

        userDto.add(linkTo(methodOn(UserController.class).getUserShapes(user.getId())).withRel("user-shapes"));
        return userDto;
    }
}
