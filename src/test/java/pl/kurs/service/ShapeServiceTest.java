package pl.kurs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.model.Shape;
import pl.kurs.model.User;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.factory.TypesFactory;
import pl.kurs.model.figures.Circle;
import pl.kurs.model.figures.Rectangle;
import pl.kurs.model.searchcriteria.Parameters;
import pl.kurs.repository.ShapeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeServiceTest {

    @InjectMocks
    private ShapeService shapeService;
    @Mock
    private ShapeRepository shapeRepository;
    private TypesFactory typesFactory;

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

//    @Test
//    void shouldAddShape() {
//        CreateShapeCommand command = CreateShapeCommand.builder()
//                .type("circle")
//                .date(LocalDateTime.now())
//                .user(user1)
//                .parameter(parameters1)
//                .build();
//        Circle shape = Circle.builder()
//                .radius(command.getParameter().getRadius())
//                .createdBy(command.getUser())
//                .createdDate(command.getDate())
//                .build();
//        ShapeInterface<Circle> circleImpl = new CircleImpl();
//        Mockito.when(shapeRepository.saveAndFlush(typesFactory.findStrategy(command.getType()).mapCommandToEntity(command)))
//                .thenReturn(shape);
////        Mockito.when(typesFactory.findStrategy(command.getType()).mapCommandToEntity(command))
////                .thenReturn(circleImpl);
//        Shape saved = shapeService.addShape(command);
//
//        assertEquals(saved.getCreatedBy(), shape.getCreatedBy());
//        Mockito.verify(shapeRepository).saveAndFlush(shape);
//    }

    @Test
    void shouldFindShapeByUser() {
       Circle shape = Circle.builder()
                .radius(parameters1.getRadius())
                .createdBy(user1)
                .createdDate(LocalDateTime.now())
                .build();
        Mockito.when(shapeRepository.findAllByCreatedBy_Id(1))
                .thenReturn(List.of(shape));
        List<Shape> shapes = shapeService.findByUser(1);
        assertEquals(shapes.get(0), shape);
        Mockito.verify(shapeRepository).findAllByCreatedBy_Id(1);
    }
    
    @Test
    void shouldFindShapeById() {
        Circle shape = Circle.builder()
                .radius(parameters1.getRadius())
                .createdBy(user1)
                .createdDate(LocalDateTime.now())
                .build();
        Mockito.when(shapeRepository.findById(1))
                .thenReturn(Optional.ofNullable(shape));
        Shape saved = shapeService.findByShapeId(1);
        assertEquals(saved.getCreatedBy(), shape != null ? shape.getCreatedBy() : null);
        Mockito.verify(shapeRepository).findById(1);
    }

    @Test
    void shouldEditShape() {
        Rectangle rectangle = Rectangle.builder()
                .height(parameters2.getHeight())
                .width(parameters2.getWidth())
                .createdBy(user2)
                .createdDate(LocalDateTime.now())
                .build();
        UpdateShapeCommand command = UpdateShapeCommand.builder()
                .version(0)
                .date(rectangle.getCreatedDate().plusHours(2))
                .parameter(parameters2)
                .user(user1)
                .build();
        Mockito.when(shapeRepository.saveAndFlush(rectangle))
                .thenReturn(rectangle);
        Shape edited = shapeService.editShape(rectangle, command);
        assertEquals(user1, edited.getCreatedBy());
        assertEquals(command.getDate(), rectangle.getCreatedDate());
        Mockito.verify(shapeRepository).saveAndFlush(rectangle);
    }


}