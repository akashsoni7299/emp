package com.crms.crm.controller;

import com.crms.crm.payload.EmployeeDto;
import com.crms.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addEmployee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto dto = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public String deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return "deleted";
    }

    @PutMapping
    public String updateEmployee(@RequestParam Long id, @RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(id,employeeDto);
        return "Employee updated successfully";
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(
            @RequestParam(name = "pageSize",required = false,defaultValue = "5")int pageSize,
            @RequestParam(name = "pageNo", required = false, defaultValue = "0")int pageNo,
            @RequestParam(name = "sortBy", required = false, defaultValue = "0")String sortBy

    ) {
        List<EmployeeDto> employees = employeeService.getAllEmployees(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
