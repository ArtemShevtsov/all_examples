package learn.generics;

public class Box<T extends B> {
    private T field;

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
