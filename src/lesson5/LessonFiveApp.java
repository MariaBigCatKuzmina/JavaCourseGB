package lesson5;

public class LessonFiveApp {
    public static void main(String[] args) {
        Employee[] Employees = new Employee[5];
        Employees[0] = new Employee("Ivanov Ivan Ivanovich", 43, "Engineer",
                         "89123456789","IvanovII@companyname.ru", 95000);
        Employees[1] = new Employee("Smirnov Anton Evgenyevich", 29, "Manager",
                "83455335678","SmirnovAE@companyname.ru", 65000);
        Employees[2] = new Employee("Perova Anna Anatolyevna", 33, "Data Analytic",
                "87659237765","PerovaAA@companyname.ru", 100000);
        Employees[3] = new Employee("Sidorov Ilya Petrovich", 53, "Chief",
                "89008763452","SidorovIP@companyname.ru", 155000);
        Employees[4] = new Employee("Kozlova Inna Ivanovna", 40, "Engineer",
                "87653479812","KozlovaII@companyname.ru", 80000);

        for (Employee empl: Employees) {
            if (empl.getAge() > 40)
                empl.printEmployee();
        }

    }
}
