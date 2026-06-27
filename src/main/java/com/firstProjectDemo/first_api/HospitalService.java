package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    public HospitalService(DoctorRepository doctorRepository,PatientRepository patientRepository){
        this.patientRepository=patientRepository;
        this.doctorRepository=doctorRepository;

    }

    public void admitPatient(PatientRequestDTO patientRequestDTO){
        Doctor doctor = doctorRepository.findById(patientRequestDTO.doctorId())
                .orElseThrow(()-> new DoctorNotFoundException("doctor not found for the said "+patientRequestDTO.doctorId()+"id"));

        Patient patient = new Patient();
        patient.setName(patientRequestDTO.patientName());
        patient.setConsultationFee(patientRequestDTO.fee());
        patient.setDoctor(doctor);

        patientRepository.save(patient);
    }

    public List<DoctorSummaryDTO> getDoctorsSummaries(){
        List<Doctor> doctors= doctorRepository.findAll();

        return doctors.stream()
                .map(doctor -> {
                    int count = doctor.getPatientList().size();

                    double revenue=doctor.getPatientList().stream()
                            .mapToDouble(Patient::getConsultationFee)
                            .sum();
                    return new DoctorSummaryDTO(
                            doctor.getId(),
                            doctor.getName(),
                            doctor.getDepartment(),
                            count,
                            revenue
                    );
                }).collect(Collectors.toList());


    }
}



