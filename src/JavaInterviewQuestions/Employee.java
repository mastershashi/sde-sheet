package JavaInterviewQuestions;

import java.util.ArrayList;
import java.util.List;
public class Employee {
    private String employeeId;
    private String employeeName;
    private int employeeSalary;
    private String employeeDepartment;
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public int getEmployeeSalary() {
        return employeeSalary;
    }
    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    public String getEmployeeDepartment() {
        return employeeDepartment;
    }
    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
    Employee(String employeeId, String employeeName, int employeeSalary, String employeeDepartment){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeDepartment = employeeDepartment;
    }
    public static List<Employee> getEmployees(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("001","Shashi",10000,"IT"));
        list.add(new Employee("002","Ravi",20000,"BCM"));
        list.add(new Employee("003","Sourabh",5000,"CS"));
        list.add(new Employee("004","Kishlay",8000,"BCA"));
        list.add(new Employee("005","Lucky",12000,"IT"));
        list.add(new Employee("006","Subhash",19000,"CS"));
        return list;

    }
}
