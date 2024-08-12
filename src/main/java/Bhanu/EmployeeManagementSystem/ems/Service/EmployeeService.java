package Bhanu.EmployeeManagementSystem.ems.Service;

import Bhanu.EmployeeManagementSystem.ems.Dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeid);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto updateEmployee(Long id,EmployeeDto updateEmployee);
   void deleteEmployee(Long id);

}
