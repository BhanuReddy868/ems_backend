package Bhanu.EmployeeManagementSystem.ems.Repository;

import Bhanu.EmployeeManagementSystem.ems.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
