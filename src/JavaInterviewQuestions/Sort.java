//Q. Given an Employee class having properties ( Id, Name, Salary, Department). Sort the Employee list on the basis or salary  
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
class Sort{
    public static void main(String[] args) {
        //method 1 using comparator 
        List<Employee> empList = Employee.getEmployees();
       /*  Collections.sort(empList,new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o1.getEmployeeSalary() - o2.getEmployeeSalary();
            };
        });*/
        empList.stream().forEach(emp -> System.out.println(emp.getEmployeeSalary()));
        // method 2 using lambda 
        System.out.println("=====Using lambda ======");
        //Collections.sort(empList,(o1,o2) ->(o2.getEmployeeSalary()- o1.getEmployeeSalary()));
        //empList.stream().forEach(emp -> System.out.println(emp.getEmployeeSalary()));

        // method 3 using stream.sorted and sysout in single line
        System.out.println("=====Using stream ======");
        empList.stream().sorted((o1 , o2) -> (o2.getEmployeeSalary() - o1.getEmployeeSalary())).forEach(emp -> System.out.println(emp.getEmployeeSalary()));
        
        // method 4 using comparing and method reference in single line
        System.out.println("=====Using comparator.comparing ======");
        empList.stream().sorted(Comparator.comparing(Employee::getEmployeeName).reversed()).forEach(emp -> System.out.println(emp.getEmployeeName()));
    }
}