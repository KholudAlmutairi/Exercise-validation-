package com.example.even.Controller;

import com.example.even.Api.ApiResponse;
import com.example.even.Model.Even;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/even")
public class evenController {
    ArrayList<Even> evens=new ArrayList<Even>();
    //• Display all event .
    @GetMapping("/get")
    public ResponseEntity getEven() {

        return ResponseEntity.status(200).body(evens);
    }
    //Add event
    @PostMapping("/add")
    public ResponseEntity addEven(@RequestBody @Valid Even even, Errors errors){
       if(errors.hasErrors()){
           String massage =errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(massage);
       }
        evens.add(even);
        return ResponseEntity.status(200).body(new ApiResponse("even added")) ;
    }

    // Update an event
    @PutMapping("/update/{id}")
    public ResponseEntity updateEven(@PathVariable int id, @RequestBody @Valid Even even,Errors errors){
        if(errors.hasErrors()){
            String massage= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        for (int i = 0; i < evens.size(); i++) {
            if (evens.get(i).getId() == id) {
                evens.set(i, even);
                return ResponseEntity.status(400).body( new ApiResponse("even not found") );
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("even updated")) ;
    }

    // Delete an event
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEven(@PathVariable int id){
        for (int i = 0; i < evens.size(); i++) {
            if (evens.get(i).getId() == id) {
                evens.remove(i);
                return ResponseEntity.status(200).body( new ApiResponse("even deleted")) ;
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("even not found") );
    }

    // Change capacity
    @PutMapping("/change/{id}")
    public ResponseEntity changeCapacity(@PathVariable int id, @RequestBody Even even) {
        for (Even event : evens) {
            if (event.getId() == id) {
                event.setCapacity(even.getCapacity());
                return ResponseEntity.status(200).body(new ApiResponse("even capacity changed"));
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("even not found"));
    }

    // Search for an event by given id
    @GetMapping("/search/{id}")
    public ResponseEntity searchEven(@PathVariable int id) {
        for (Even event : evens) {
            if (event.getId() == id) {
                return ResponseEntity.status(200).body(new ApiResponse("event exists"));
            }
        }
        return ResponseEntity.status(200).body( new ApiResponse("event does not exist"));
    }

}

