package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.figures.Circle;
import pl.kurs.model.figures.Rectangle;
import pl.kurs.model.figures.Triangle;
import pl.kurs.model.searchcriteria.Parameters;
import pl.kurs.repository.ShapeRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShapeServiceTest<T extends Shape> {

    @InjectMocks
    private ShapeService<T> shapeService;
    @Mock
    private ShapeRepository<Circle> circleRepositoryMock;
    @Mock
    private ShapeRepository<Rectangle> rectangleRepositoryMock;
    @Mock
    private ShapeRepository<Triangle> triangleRepositoryMock;


    private User user1, user2;
    private Parameters parameters1, parameters2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        user2 = User.builder()
                .name("Pawel")
                .surname("Adamek")
                .email("pablo123@gmail.com")
                .build();
        parameters1 = Parameters.builder()
                .radius(10.0)
                .build();
        parameters2 = Parameters.builder()
                .width(5.0)
                .height(7.0)
                .build();
    }

    @Test
    void shouldAddShape() {
//        UpdateShapeCommand command = UpdateShapeCommand.builder()
//                .user(user2)
//                .parameter(parameters1)
//                .date(LocalDateTime.now().plusHours(2))
//                .version(0)
//                .build();
//        Circle shape = Circle.builder()
//                .createdBy(user1)
//                .radius(parameters1.getRadius())
//                .createdDate(LocalDateTime.now())
//                .build();
//        Mockito.when(circleRepositoryMock.saveAndFlush(shape))
//                .thenReturn(shape);
//
//        T saved = shapeService.editShape((T) shape, command);
//
//        Mockito.verify(circleRepositoryMock).saveAndFlush(saved);
    }



}