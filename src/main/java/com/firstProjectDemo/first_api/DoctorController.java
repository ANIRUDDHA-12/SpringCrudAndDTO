package com.firstProjectDemo.first_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {
    private final HospitalService hospitalService;
    private final DoctorRepository doctorRepository;

    public DoctorController(HospitalService hospitalService,DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
        this.hospitalService=hospitalService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> seedDoctor(@RequestBody Doctor doctor){
        Doctor doctorDb = doctorRepository.save(doctor);
        return new ResponseEntity<>(doctorDb, HttpStatus.CREATED);
    }

    @PostMapping("/patients")
    public ResponseEntity<String> admitPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        hospitalService.admitPatient(patientRequestDTO);
        return new ResponseEntity<>("Patient admitted successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/doctors/summary")
    public ResponseEntity<List<DoctorSummaryDTO>> getDoctorsSummaries(){
        List<DoctorSummaryDTO> summaries = hospitalService.getDoctorsSummaries();
        return ResponseEntity.ok(summaries);
    }
}
