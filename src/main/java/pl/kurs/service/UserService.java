package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.error.EntityNotFoundException;
import pl.kurs.model.User;
import pl.kurs.model.command.CreateUserCommand;
import pl.kurs.model.command.UpdateUserCommand;
import pl.kurs.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(CreateUserCommand command) {
        return userRepository.saveAndFlush(User.builder()
                .name(command.getName())
                .surname(command.getSurname())
                .email(command.getEmail())
                .build());
    }

    @Transactional(readOnly = true)
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", String.valueOf(id)));
    }

    @Transactional
    public void softDelete(int id) {
        User toDelete = userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("User", String.valueOf(id)));
        if(!toDelete.isDeleted()) {
            toDelete.setDeleted(true);
        }
    }

    @Transactional
    public User editUser(User user, UpdateUserCommand command) {
        user.setName(command.getName());
        user.setSurname(command.getSurname());
        user.setVersion(command.getVersion());
        return userRepository.saveAndFlush(user);
    }
}
