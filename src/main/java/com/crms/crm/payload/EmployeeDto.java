package com.crms.crm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class EmployeeDto {
    private Long id;

    @Size(min=3,message = "At least 3 characters required for name")
    @NotBlank
    private String name;

    @Email
    private String emailId;

    @Size(min=10, max=10, message = "10 digits required for mobile")
    private String mobile;

}
