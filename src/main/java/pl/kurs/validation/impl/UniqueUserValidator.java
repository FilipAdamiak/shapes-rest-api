package pl.kurs.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kurs.model.User;
import pl.kurs.repository.UserRepository;
import pl.kurs.validation.annotation.UniqueUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, User> {

    private final UserRepository userRepository;

    @Autowired
    public UniqueUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(user.getEmail());
    }

}
