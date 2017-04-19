package application.validator;

@FunctionalInterface
public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}
