public class Vehicle {

    interface Moveable {
        void move();

        void open(); // добавил метод open
    }

    interface Stopable {
        void stop();
    }

    abstract class Car implements Moveable, Stopable { // не реализовывались интерфейсы
        private Engine engine; // инкапсуляция Engine
        private String color;
        private String name;

        void start() {
            System.out.println("Car starting");
        }

        @Override // метод не должен быть абстрактным, хоть это и не обязательно, но добавил аннотацию @Override
        public void open() {

        }

        public Engine getEngine() {
            return engine;
        }

        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // не реализован метод stop
        @Override
        public void stop() {

        }
    }

    class LightWeightCar extends Car {

        @Override
        public void open() {
            System.out.println("Car is open");
        }

        @Override
        public void move() {
            System.out.println("Car is moving");
        }

    }

    class Lorry extends Car {

        public void move() {
            System.out.println("Car is moving");
        }

        // не был реализован абстрактный метод open
        @Override
        public void open() {

        }

        public void stop() {
            System.out.println("Car is stop");
        }
    }

    class Engine {

    }


}
