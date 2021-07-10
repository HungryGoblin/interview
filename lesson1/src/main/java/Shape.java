public interface Shape {
    public void setParameters(String[] parameters);
    public void parseParameters(String[] parameters);
    public void draw();
    public void setShapeName(String shapeName);
    public String getShapeName();
}
