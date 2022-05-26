package pl.kurs.error.constraints;

public interface ConstraintErrorHandler {
    String constraintName();

    String message();

    String field();
}
