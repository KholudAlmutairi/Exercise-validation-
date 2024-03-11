package com.example.projectq2.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    // project Class : ID , title , description , status, companyName

    @NotNull(message =" id cannot be null" ) //Cannot be null
    //@Size(min = 2,message =" id length more than 2 ")//Length more than 2
    @Min(3)
    private Integer id;

    @NotNull(message = "title Cannot be null")//Cannot be null
    @Size(min =9,message = " title length more than 8")//length more than 8
    private String title;

    @NotNull(message = "description cannot be null")//Cannot be null
    @Size(min = 16,message = "description length more than 15") //Length more than 15
    private String description;


    //private boolean status;
    @NotNull(message ="status cannot be null" )//Cannot be null
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be Not Started, In Progress, or Completed only")//must be Not Started or in Progress or Completed only
    private String status;

    @NotNull(message = "companyName cannot be null")//Cannot be null
    @Size(min = 7,message = "companyName length more than 6")//Length more than 6
    private String companyName;}




//Cannot be null
//must be Not Started or in Progress or Completed only
