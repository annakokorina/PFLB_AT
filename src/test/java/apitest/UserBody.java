package apitest;

public class UserBody {
    public int id;
    public String firstName;
    public String secondName;
    public double money;
    public int age;
    public Sex sex;

    public UserBody(int id, String firstName, String secondName, double money, int age, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.money = money;
        this.age = age;
        this.sex = sex;
    }

    public enum Sex {
        MALE, FEMALE
    }


    public UserBody(){ }

    int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public double getMoney() {
        return money;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }
}