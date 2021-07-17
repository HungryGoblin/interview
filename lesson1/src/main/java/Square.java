public class Square extends ProtoShape implements Shape {

    private int sideA;

    @Override
    public void parseParameters(String[] parameters) {
        sideA = Integer.parseInt(parameters[0]);
    }

    @Override
    public void draw() {
        // код отрисовки квадрата
        super.draw();
    }

    Square(String[] parameters) {
        super(parameters);
        setShapeName("Квадрат");
    }

}
