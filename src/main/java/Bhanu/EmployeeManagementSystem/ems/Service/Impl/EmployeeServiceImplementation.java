package Bhanu.EmployeeManagementSystem.ems.Service.Impl;
import Bhanu.EmployeeManagementSystem.ems.Dto.EmployeeDto;
import Bhanu.EmployeeManagementSystem.ems.Entity.Employee;
import Bhanu.EmployeeManagementSystem.ems.Exception.ResourceNotFoundException;
import Bhanu.EmployeeManagementSystem.ems.Mapper.EmployeeMapper;
import Bhanu.EmployeeManagementSystem.ems.Repository.EmployeeRepository;
import Bhanu.EmployeeManagementSystem.ems.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
   private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        String combinedAddress=mergeAddressFields(employeeDto.getHouseNumber(),employeeDto.getStreet(),employeeDto.getCity(),employeeDto.getState(),employeeDto.getCountry());
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        employee.setAddress(combinedAddress);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    private  String mergeAddressFields(String houseNumber,String street,String city,String state,String country){
        return String.format("%s,%s,%s,%s,%s",houseNumber,street,city,state,country);
    }

    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmployeeService.class);
    @Override
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()->{
                    String error="Employee is not Exist with the given ID."+id;
                    logger.error(error);
                    return new RuntimeException(error);
                });
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
    @Override
    public List<EmployeeDto> getAllEmployee() {
        String error1="List is empty";
        List<Employee> employees = employeeRepository.findAll();
//        if(employees.isEmpty()){
//            logger.error(error1);
//            return (List<EmployeeDto>) new RuntimeException(error1);
//        }
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee) {
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("employee doesn't exist with id" +id)
        );
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        String combinedAddress = mergeAddressFields(
                updateEmployee.getHouseNumber(),
                updateEmployee.getStreet(),
                updateEmployee.getCity(),
                updateEmployee.getState(),
                updateEmployee.getCountry()

        );
        employee.setAddress(combinedAddress);

        Employee updatedEmp = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmp);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()->{
                    String error="Employee is not Exist with the given ID."+id;
                    logger.error(error);
                    return new RuntimeException(error);
                });
        employeeRepository.deleteById(id);
    }

}
