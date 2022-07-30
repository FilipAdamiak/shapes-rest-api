package pl.kurs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.model.User;
import pl.kurs.model.command.CreateUserCommand;
import pl.kurs.model.command.UpdateUserCommand;
import pl.kurs.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @InjectMocks
    private UserService userServiceMock;
    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddUser() {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        CreateUserCommand command = CreateUserCommand.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Mockito.when(userRepositoryMock.saveAndFlush(user1))
                .thenReturn(user1);
        userServiceMock.addUser(command);
        Mockito.verify(userRepositoryMock).saveAndFlush(user1);
    }

    @Test
    void shouldDeleteDoctor() {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Mockito.when(userRepositoryMock.findById(1))
                .thenReturn(Optional.ofNullable(user1));
        userServiceMock.softDelete(1);
        Mockito.verify(userRepositoryMock).findById(1);
    }

    @Test
    void shouldEditUser() {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        UpdateUserCommand update = UpdateUserCommand.builder()
                .name("Staszek")
                .surname("Pawlak")
                .version(0)
                .build();
        Mockito.when(userRepositoryMock.saveAndFlush(user1))
                .thenReturn(user1);
        User edited = userServiceMock.editUser(user1, update);
        Mockito.verify(userRepositoryMock).saveAndFlush(user1);
        assertEquals("Staszek", edited.getName());
        assertEquals("Pawlak", edited.getSurname());
        assertEquals("adas123@gmail.com", edited.getEmail());
    }


}