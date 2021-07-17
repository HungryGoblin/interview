public class Circle extends ProtoShape implements Shape {

    private int radius;

    @Override
    public void parseParameters(String[] parameters) {
        radius = Integer.parseInt(parameters[0]);
    }

    @Override
    public void draw() {
        // код отрисовки круга
        super.draw();
    }

    Circle(String[] parameters) {
        super(parameters);
        setShapeName("Круг");
    }

}
