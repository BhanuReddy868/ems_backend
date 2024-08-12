package Bhanu.EmployeeManagementSystem.ems.Mapper;
import Bhanu.EmployeeManagementSystem.ems.Dto.EmployeeDto;
import Bhanu.EmployeeManagementSystem.ems.Entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        String[] addressParts = employee.getAddress().split(",");
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
                addressParts.length > 0? addressParts[0]:"",
                addressParts.length > 1? addressParts[1]:"",
                addressParts.length > 2? addressParts[2]:"",
                addressParts.length > 3? addressParts[3]:"",
                addressParts.length > 4? addressParts[4]:""
        );
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        String combinedAddress = String.format("%s,%s,%s,%s,%s",
                employeeDto.getHouseNumber(),
                employeeDto.getStreet(),
                employeeDto.getCity(),
                employeeDto.getState(),
                employeeDto.getCountry());
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                combinedAddress
        );
    }
}
