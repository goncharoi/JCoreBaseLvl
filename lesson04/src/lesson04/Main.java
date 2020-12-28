package lesson04;

public class Main {

    public static void main(String[] args) {
//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
//2 Конструктор класса должен заполнять эти поля при создании объекта;
//3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
        //см. класс Employee
//4 Вывести при помощи методов из пункта 3 ФИО и должность.
        Employee lvHead = new Employee("Yoda", "Master", "+7(919)678-54-23", 999.99f, (byte) 127);
        System.out.println("Head of our company is " + lvHead.getMvJob() + " " + lvHead.getMvFullName());
//5 Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        //1 уже создан выше, статический массив - внутри класса
        new Employee("Vanya", "coder", "none", 100f, (byte) 23);
        new Employee("Petya", "designer", "+7(3422)66-54-32", 200f, (byte) 33);
        new Employee("San Sanych", "manager", "+7(3422)66-54-43", 300f, (byte) 43);
        new Employee("Marya Ivanovna", "manager", "+7(3422)66-54-12", 300f, (byte) 45);
        Employee.printCompany();
//6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
        Employee.indexSalary();
        Employee.printCompany();
    }
}

