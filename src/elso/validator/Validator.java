package elso.validator;

public interface Validator<T> {
    boolean IsValid(T object);
}
