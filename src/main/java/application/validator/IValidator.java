package application.validator;

public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}
