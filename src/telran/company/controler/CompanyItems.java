package telran.company.controler;

import telran.company.dto.Employee;
import telran.company.service.*;
import telran.view.Item;
import telran.view.inputOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.HashSet;

import static telran.view.Item.*;


public class CompanyItems {
    private static final int MIN_SALARY = 5500;
    private static final int MAX_SALARY = 50000;
    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 70;
    static Set<String> departments = new HashSet<>(Arrays.asList("Developmnet", "QA","Audit", "Accounting", "Managment", "Sales"));
    static CompanyService company;
    static  String fileName;
    public static Item[] getItems(CompanyService companyService, String file){
        company = companyService;
        fileName = file;
        Item[] items = {
                of("Hire employee", io -> hireEmployee(io)),
                of("Fire employee", io -> fireEmployee(io)),
                of("Get employee", io -> getEmployee(io)),
                of("Display data about employees in department", io -> getEmployeesByDepartment(io)),
                of("Display data about all employees", io -> getAllEmployees(io)),
                of("Display employees by salary", io -> getEmployeesBySalary(io)),
                of("Display employees by age", io -> getEmployeeByAge(io)),
                of("Display salary distribution by department", io -> salaryDistributionByDepartments(io)),
                of("Display salary distribution employees", io -> getSalaryDistribution(io)),
                of("Update department of employee", io -> updateDepartment(io)),
                of("Update salary of employee", io -> updateSalary(io)),
                of("Exit and save all", io -> saveEmployees(io), true),

        };
        return items;
    }

    private static void hireEmployee(inputOutput io) {
        long id = io.readLong("Enter id", "Wrong id");
        if(company.getEmployee(id) != null){
            throw new RuntimeException("Employee with entered id allready exist");
        }
        String name = io.readPredicate("Enter name", "Wrong name", str -> checkName(str));
        int salary = io.readInt("Enter salary", "Wrong salary", MIN_SALARY, MAX_SALARY);
        String department  = io.readOption("Enter department" + departments,"Wrong department", departments);
        LocalDate birthDate = io.readDate("Enter birthdate", "Wrong date", getDate(MAX_AGE), getDate(MIN_AGE));
        Employee empl = new Employee(id, name, salary, department, birthDate);
        company.hireEmployee(empl);
    }

    private static boolean checkName(String str) {
        boolean res = false;
        if(str.length() >=3){
            if(Character.isUpperCase(str.charAt(0))){
                if(str.substring(1).chars().allMatch(c->Character.isLowerCase(c))){
                    res = true;
                }
            }
        }
        return res;
    }

    private static LocalDate getDate(int age) {
        return LocalDate.now().minusYears(age);
    }

    private static void fireEmployee(inputOutput io) {
        Long id = io.readLong("Enter id", "Wrong id");
        company.fireEmployee(id);
        io.writeLine("Employee has been fired succesfully");
    }

    private static void getEmployee(inputOutput io) {
        Long id = io.readLong("Enter id", "Wrong id");
        Employee employee = company.getEmployee(id);
        String res = employee == null ? "employee not found" : employee.toString();
        io.writeLine(res);

    }

    private static void getEmployeesByDepartment(inputOutput io) {
        String department = io.readOption("Enter department" + departments,"Wrong department", departments);
        displayList(io, company.getEmployeesByDepartment(department));
    }

    private static void getAllEmployees(inputOutput io) {
        displayList(io, company.getAllEmployees());
    }

    private static void getEmployeesBySalary(inputOutput io) {
        int salaryFrom = io.readInt("Enter salary from", "wrong salary", MIN_SALARY, MAX_SALARY-1);
        int salaryTo = io.readInt("Enter salary to", "wrong salary",MIN_SALARY+1, MAX_SALARY);
        displayList(io, company.getEmployeesBySalary(salaryFrom, salaryTo));
    }

    private static void getEmployeeByAge(inputOutput io) {
        int fromAge = io.readInt("Enter age from", "Wrong age", MIN_AGE, MAX_AGE-1);
        int toAge = io.readInt("Enter age to", "Wrong age", fromAge+1, MAX_AGE);
        displayList(io, company.getEmployeeByAge(fromAge, toAge));
    }

    private static void salaryDistributionByDepartments(inputOutput io) {
        displayList(io, company.salaryDistributionByDepartments());
    }

    private static void getSalaryDistribution(inputOutput io) {
        int interval = io.readInt("Enter interval", "Wrong interval", 500, 5000);
        displayList(io, company.getSalaryDistribution(interval));

    }

    private static void updateDepartment(inputOutput io) {
        long id = io.readLong("Enter id", "Wrong id");
        String department = io.readOption("Enter department" + departments,"Wrong department", departments);
        company.updateDepartment(id, department);
        io.writeLine("Department update");

    }

    private static void updateSalary(inputOutput io) {
        long id = io.readLong("Enter id", "Wrong id");
        int salary = io.readInt("Enter salary value", "Wrong salary", MIN_SALARY, MAX_SALARY);
        company.updateSalary(id, salary);
        io.writeLine("Salary update");
    }

    private static void saveEmployees(inputOutput io) {
        company.save(fileName);

    }

    private static <T> void displayList(inputOutput io, List<T> list){
        list.forEach(io::writeLine);
    }
}
