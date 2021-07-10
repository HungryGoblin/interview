public class Triangle extends ProtoShape implements Shape {

    private int sideA;
    private int sideB;
    private int sideC;

    @Override
    public void parseParameters(String[] parameters) {
        sideA = Integer.parseInt(parameters[0]);
        sideB = Integer.parseInt(parameters[1]);
        sideC = Integer.parseInt(parameters[2]);
    }

    @Override
    public void draw() {
        // код отрисовки треугольника
        super.draw();
    }

    Triangle(String[] parameters) {
        super(parameters);
        setShapeName("Треугольник");
    }

}
