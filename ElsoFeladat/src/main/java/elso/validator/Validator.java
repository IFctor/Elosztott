package elso.validator;

public interface Validator<T> {
    boolean isValid(T object);
}
