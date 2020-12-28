package lesson04;

import java.lang.reflect.Array;
import java.util.Arrays;

//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
public class Employee {
    private String mvFullName;
    private String mvJob;
    private String mvPhone;
    private float mvSalary;
    private byte mvAge;
    private int mvNumber;

    private static Employee[] ltCompany = new Employee[0];
    private static int mvCounter = 0;

    //2 Конструктор класса должен заполнять эти поля при создании объекта;
    Employee(String ivFullName, String ivJob, String ivPhone, float ivSalary, byte ivAge) {
        setMvFullName(ivFullName);
        setMvJob(ivJob);
        setMvPhone(ivPhone);
        setMvSalary(ivSalary);
        setMvAge(ivAge);
//7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.
        mvNumber = ++mvCounter;
        //запихиваем нового сотрудника в статический массив ltCompany
        Employee[] ltTempCompany = Arrays.copyOf(ltCompany, ltCompany.length + 1);
        ltTempCompany[ltCompany.length] = this;
        ltCompany = ltTempCompany;
    }

    //3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
    public String getMvFullName() {
        return mvFullName;
    }

    public String getMvJob() {
        return mvJob;
    }

    public String getMvPhone() {
        return mvPhone;
    }

    public float getMvSalary() {
        return mvSalary;
    }

    public byte getMvAge() {
        return mvAge;
    }
    public int getMvNumber(){
        return mvNumber;
    }
    public Employee getEmployee(int ivIndex) {
        return ltCompany[ivIndex];
    }

    public void setMvFullName(String ivFullName) {
        mvFullName = ivFullName;
    }

    public void setMvJob(String ivJob) {
        mvJob = ivJob;
    }

    public void setMvPhone(String ivPhone) {
        mvPhone = ivPhone;
    }

    public void setMvSalary(float ivSalary) {
        mvSalary = ivSalary;
    }

    public void setMvAge(byte ivAge) {
        mvAge = ivAge;
    }

    public static void printCompany() {
        System.out.println( "Табельный №\tФИО\tВозраст\tЗарплата,руб");
        for (Employee lvEmployee : ltCompany) {
            System.out.println( lvEmployee.getMvNumber() + "\t" + lvEmployee.getMvFullName() + "\t" + lvEmployee.getMvAge() + "\t" + lvEmployee.getMvSalary());
        }
    }

    //6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
    public static void indexSalary() {
        for (Employee lvEmployee : ltCompany) {
            if (lvEmployee.getMvAge() > 35) lvEmployee.setMvSalary(lvEmployee.getMvSalary() + 10000f);
        }
    }
}
