
//Q. Given an Employee class having properties ( Id, Name, Salary, Department). Sort the Employee list on the basis or salary  
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Sort {
    static class Employee {
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

        Employee(String employeeId, String employeeName, int employeeSalary, String employeeDepartment) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.employeeSalary = employeeSalary;
            this.employeeDepartment = employeeDepartment;
        }

        public static List<Employee> getEmployees() {
            List<Employee> list = new ArrayList<>();
            list.add(new Employee("001", "Shashi", 10000, "IT"));
            list.add(new Employee("002", "Ravi", 20000, "BCM"));
            list.add(new Employee("003", "Sourabh", 5000, "CS"));
            list.add(new Employee("004", "Kishlay", 8000, "BCA"));
            list.add(new Employee("005", "Lucky", 12000, "IT"));
            list.add(new Employee("006", "Subhash", 19000, "CS"));
            return list;

        }
    }

    public static void main(String[] args) {
        // method 1 using comparator
        List<Employee> empList = Employee.getEmployees();
        /*
         * Collections.sort(empList,new Comparator<Employee>() {
         * public int compare(Employee o1, Employee o2) {
         * return o1.getEmployeeSalary() - o2.getEmployeeSalary();
         * };
         * });
         */
        empList.stream().forEach(emp -> System.out.println(emp.getEmployeeSalary()));
        // method 2 using lambda
        System.out.println("=====Using lambda ======");
        // Collections.sort(empList,(o1,o2) ->(o2.getEmployeeSalary()-
        // o1.getEmployeeSalary()));
        // empList.stream().forEach(emp -> System.out.println(emp.getEmployeeSalary()));

        // method 3 using stream.sorted and sysout in single line
        System.out.println("=====Using stream ======");
        empList.stream().sorted((o1, o2) -> (o2.getEmployeeSalary() - o1.getEmployeeSalary()))
                .forEach(emp -> System.out.println(emp.getEmployeeSalary()));

        // method 4 using comparing and method reference in single line
        System.out.println("=====Using comparator.comparing ======");
        empList.stream().sorted(Comparator.comparing(Employee::getEmployeeName).reversed())
                .forEach(emp -> System.out.println(emp.getEmployeeName()));
    }
}