package pl.kurs.error.constraints;

import org.springframework.stereotype.Service;

@Service
public class UserEmailUniqueHandler implements ConstraintErrorHandler {
    @Override
    public String constraintName() {
        return "UC_USER_EMAIL";
    }

    @Override
    public String message() {
        return "EMAIL_NOT_UNIQUE";
    }

    @Override
    public String field() {
        return "email";
    }
}
