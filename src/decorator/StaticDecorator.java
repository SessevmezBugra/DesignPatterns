package decorator;

import java.awt.*;
import java.util.function.Supplier;

class ColoredShape2<T extends Shape> implements Shape {

    private Shape shape;
    private String color;

    public ColoredShape2(Supplier<? extends T> ctor, String color) {
        this.shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape2<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape2(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}

class DynamicDecoratorTest {
    public static void main(String[] args) {
        ColoredShape2<Square> blueSquare =
            new ColoredShape2<>(
                    () -> new Square(20),
                    "blue"
            );
        System.out.println(blueSquare.info());
        TransparentShape2<ColoredShape2<Circle>> myCircle =
                new TransparentShape2(
                        () -> new ColoredShape2<>(
                                () -> new Circle(5), "green"
                        ), 50);

        TransparentShape2<ColoredShape2<Circle>> myCircle2 =
                new TransparentShape2(
                        () -> null, 50);
        System.out.println(myCircle.info());

    }
}