import java.util.Arrays;

public abstract class ProtoShape implements Shape {
    private String[] parameters;
    private String shapeName;

    @Override
    public void setParameters(String[] parameters) {
        parseParameters(parameters);
        this.parameters = parameters;
    }

    @Override
    public void parseParameters(String[] parameters) {
    }

    @Override
    public void draw() {
        System.out.printf("Рисую: %s (%s) %n", getShapeName(), Arrays.toString(parameters));
    }

    @Override
    public String getShapeName() {
        return shapeName;
    }

    @Override
    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    ProtoShape(String[] parameters) {
        setParameters(parameters);
    }

}
