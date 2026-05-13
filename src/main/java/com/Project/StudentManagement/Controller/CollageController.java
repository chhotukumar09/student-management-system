package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.CollageDTO;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collage")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class CollageController {
    private final StudentService studentService;

    // get collage by id
    @GetMapping("/get/{id}")
    public ResponseEntity<CollageDTO> give(@Valid @PathVariable Long id){
        return ResponseEntity.ok(studentService.GiveCollage(id));
    }

    // add collage
    @PostMapping("/add")
    public ResponseEntity<CollageDTO> addNew(@Valid @RequestBody CollageDTO collageDTO){
        CollageDTO collagedto = studentService.addcollage(collageDTO);

        return new ResponseEntity<>(collagedto, HttpStatus.CREATED);
    }

    // get all collage details
    @GetMapping("/get/all")
    public List<CollageDTO> allCollages(){
        List<CollageDTO> collageDTOS = studentService.allCollage();
        return collageDTOS;
    }
}
