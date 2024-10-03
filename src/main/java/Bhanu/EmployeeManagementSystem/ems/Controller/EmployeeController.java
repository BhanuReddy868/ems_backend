package Bhanu.EmployeeManagementSystem.ems.Controller;

import Bhanu.EmployeeManagementSystem.ems.Dto.EmployeeDto;
import Bhanu.EmployeeManagementSystem.ems.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@Controller
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    //addEmployee rest api
    @PostMapping("/create")

    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

  //  getEmployee api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
        EmployeeDto employeeDto=employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }
    // GetAllEmployee() REST API.

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
       List<EmployeeDto> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
    //updateemployee api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDto  updateEmployee)
    {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    @DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted Successfully  ");
    }
}


