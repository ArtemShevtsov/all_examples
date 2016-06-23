package learn.generics;

public class GenericDemo {
    public static void main(String[] args) {
        Box b = new Box<B>();
        b.setField(new C());
        System.out.println(b.toString());
    }
}

class A {
    private String text = "I am a class \"A\"";

    @Override
    public String toString() {
        return "A{" +
                "text='" + text + '\'' +
                '}';
    }
}

class B extends A {
    private String text = "I am a class \"B\"";

    @Override
    public String toString() {
        return "B{" +
                "text='" + text + '\'' +
                '}';
    }
}

class C extends B {
    private String text = "I am a class \"C\"";

    @Override
    public String toString() {
        return "C{" +
                "text='" + text + '\'' +
                '}';
    }
}