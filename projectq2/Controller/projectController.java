package com.example.projectq2.Controller;

import com.example.projectq2.Api.ApiResponse;
import com.example.projectq2.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


    @RestController
    @RequestMapping("/api/v1/project")
    public class projectController {

        ArrayList<Project> projects = new ArrayList<>();

        //Display all project .
        @GetMapping("/get")
        public ResponseEntity getProjects() {
            return ResponseEntity.status(200).body(projects) ;
        }

        @PostMapping("/add")
        public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
            if(errors.hasErrors()){
                String massage =errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(massage);
            }
            projects.add(project);
            return ResponseEntity.status(200).body( new ApiResponse("project added"));
        }

        // Update a project
        @PutMapping("/update/{id}")
        public ResponseEntity updatedProject(@PathVariable int id, @RequestBody @Valid Project project,Errors errors) {
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getId() == id) {
                    projects.set(i,project);
                    return ResponseEntity.status(200).body( new ApiResponse("project updated"));
                }
            }
            return ResponseEntity.status(400).body(new ApiResponse("project not found with id: " + id));
        }

        //Delete a project
        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteProject(@PathVariable int id) {
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getId() == id) {
                    projects.remove(i);
                    return ResponseEntity.status(200).body(new ApiResponse("project deleted"));
                }
            }
            return ResponseEntity.status(400).body(new ApiResponse("project not found"));
        }

        //• Change the project status as done or not done

        @PutMapping("/change/{id}")
        public ResponseEntity changeProject(@PathVariable int id, @RequestBody @Valid String status) {
            for (Project project : projects) {
                if (project.getId() == id) {
                    project.setStatus(status);
                    return ResponseEntity.status(200).body( new ApiResponse("Project status changed"));
                }
            }
            return ResponseEntity.status(400).body(new ApiResponse("Project not found"));
        }

        //• Display All project for one company by companyName.
        @GetMapping("/getByCompany/{companyName}")
        public ResponseEntity getByCompany(@PathVariable String companyName){
            ArrayList<Project> company = new ArrayList<>();
            for (Project project:projects){
                if(project.getCompanyName().equals(companyName)){
                    company.add(project);
                    return ResponseEntity.status(200).body(new ApiResponse(("Company name does exist projects"+"  "+ company)));
                }

            }
            return ResponseEntity.status(400).body(new ApiResponse("Company does not exist projects "));
        }

        // Search for a project by given title.
        @GetMapping("/search/{title}")
        public ResponseEntity searchProject(@PathVariable String title){
            for (Project project : projects) {
                if (project.getTitle().equals(title)) {
                    return ResponseEntity.status(200).body(new ApiResponse("Project found"+" "+project));
                }
            }
            return ResponseEntity.status(400).body( new ApiResponse("Project not found"));
        }


    }

