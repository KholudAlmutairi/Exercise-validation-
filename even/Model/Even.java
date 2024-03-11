package com.example.even.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Even {
    //ID , description , capacity, startDate , endDate

    @NotNull(message = "id cannot be null") //Cannot be null
    @Min(value = 3,message = "id length more than 2") //Length more than 2
    //@Size(min = 3,message = "id length more than 2")
    //@Pattern(regexp = "\\d{3,}", message = "id length must be more than 2")
    //@Digits(integer = 3, fraction = 0, message = "id length must be more than 2")
    //@Pattern(regexp = "^\\d{3,}$", message = "ID length must be more than 2")
    //@Pattern(regexp = "^[0-9]{3,}$", message = "ID length must be more than 2")
    //@Pattern(regexp = "^\\d{2,}$", message = "ID length must be more than 2")
    private Integer id;



    @NotNull(message = "description cannot be null!") //Cannot be null
    @Size(min = 16, message = "description length more than 15")//Length more than 15
    private String description;

    @NotNull(message = "capacity Cannot be null")// Cannot be null
    @Min(value = 26,message ="capacity must be more than 25" )//It must be more than 25
    @Positive
    @Digits(integer = 5, fraction = 2, message = "Capacity must be a number") //It has to be number
    // @Pattern(regexp =  0 to 9, 1 to 9, 0 to 10, 1 to 10, 1 to 12, 1 to 16 and 1-31, 1-32, 0-99, 0-100, 1-100,1-127, 0-255, 0-999, 1-999, 1-1000 and 1-9999)
    private Double capacity;


    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}