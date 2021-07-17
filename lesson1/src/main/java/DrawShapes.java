public class DrawShapes {
    public static void main(String[] args) {
        new Circle(new String[]{"20"}).draw();
        new Square(new String[]{"15"}).draw();
        new Triangle(new String[]{"20","15","10"}).draw();
    }
}
