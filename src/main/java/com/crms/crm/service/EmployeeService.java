package com.crms.crm.service;

import com.crms.crm.entity.Employee;
import com.crms.crm.exception.ResourceNotFound;
import com.crms.crm.payload.EmployeeDto;
import com.crms.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private ModelMapper modelMapper;

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee saved = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = mapToDto(saved);

        return employeeDto1;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
    }

    public void updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = optionalEmployee.get();
        employee.setName(employeeDto.getName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setMobile(employeeDto.getMobile());
        employeeRepository.save(employee);
    }

    public List<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee) {
          EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
          return employeeDto;
    }

    Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                ()-> new ResourceNotFound("Employee not found with id: " + empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}

