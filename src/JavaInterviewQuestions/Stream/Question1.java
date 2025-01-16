import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee{
    private String employeeId;
    private String name;
    private String department;
    private String salary;
    
    public Employee(String employeeId, String name, String department, String salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public String getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Employee [EmployeeId=" + employeeId +", name="+ name + ", department=" + department + ", salary=" + salary + "]";
    }
    // }
    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
    //     result = prime * result + ((name == null) ? 0 : name.hashCode());
    //     result = prime * result + ((department == null) ? 0 : department.hashCode());
    //     result = prime * result + ((salary == null) ? 0 : salary.hashCode());
    //     return result;
    // }
    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Employee other = (Employee) obj;
    //     if (employeeId == null) {
    //         if (other.employeeId != null)
    //             return false;
    //     } else if (!employeeId.equals(other.employeeId))
    //         return false;
    //     if (name == null) {
    //         if (other.name != null)
    //             return false;
    //     } else if (!name.equals(other.name))
    //         return false;
    //     if (department == null) {
    //         if (other.department != null)
    //             return false;
    //     } else if (!department.equals(other.department))
    //         return false;
    //     if (salary == null) {
    //         if (other.salary != null)
    //             return false;
    //     } else if (!salary.equals(other.salary))
    //         return false;
    //     return true;
    // }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
public class Question1 {

    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1", "Shashi", "IT", "10000"));
        employeeList.add(new Employee("2", "Rahul", "IT", "10000"));
        employeeList.add(new Employee("3", "Nisha", "IT", "10000"));
        employeeList.add(new Employee("4", "Abhishek", "IT", "10000"));
        employeeList.add(new Employee("5", "Tapan", "IT", "10000"));

        /* You have a collection of employee objects. How would you retrieve the names of all employees and it should be sorted in alphabetical order */ 

        List<String> employeeNameList = employeeList.stream().map(Employee::getName).sorted((a,b) -> a.compareTo(b)).collect(Collectors.toList());

        employeeNameList.forEach(empName -> System.out.println(empName) );

        /* Sort employee object based on name */ 
        employeeList = employeeList.stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
        employeeList.forEach(emp -> System.out.println(emp.toString()));
    }
    
}
