//    Создать builder для класса Person со следующими полями:
//    String firstName, String lastName, String middleName, String country, String address, String phone,
//    int age, String gender.

public class Person {

    private String firstName;

    private String lastName;

    private String middleName;

    private String country;

    private String address;

    private int age;

    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Person setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Person setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Person build() {
        return this;
    }

    public static void main(String[] args) {
        Person person = new Person()
                .setLastName("Иванов")
                .setFirstName("Иван")
                .setMiddleName("Иванович")
                .setAddress("г. Москва, ул. Юольшая Лубянка 2")
                .setGender("m")
                .setCountry("Россия")
                .setAge(50)
                .build();
    }

}
