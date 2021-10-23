package lesson5;

public class Employee {
    private String fullName;
    private int age;
    private String post;
    private String eMail;
    private String phoneNumber;
    private double salary;

    public Employee(String fullName, int age, String post, String phoneNumber, String eMail, int salary) {
        this(fullName, age);
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.post = post;
    }

    public Employee(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public void printEmployee(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        char agePostfix;
        int ageMod = age % 10;
        if (ageMod >= 1 && ageMod <= 4) agePostfix = 'г';
        else agePostfix = 'л';
        return "Сотрудник: " + fullName +
                ", " + age + agePostfix +
                "., должность: " + post +
                ", зарплата: " + salary +
                ", тел.: " + formatPhone(phoneNumber) +
                ", e-Mail: " + eMail;

   }
    private String formatPhone(String phoneNumber)
    {
        return phoneNumber.substring(0,1) + "("+phoneNumber.substring(1,4)+")"+
                phoneNumber.substring(4,7)+"-"+phoneNumber.substring(7,9)+"-"+phoneNumber.substring(9,11);
    }

    public int getAge() {
        return age;
    }
}
