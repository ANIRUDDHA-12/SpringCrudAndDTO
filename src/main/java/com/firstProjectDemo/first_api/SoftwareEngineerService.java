package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(
            SoftwareEngineerRepository softwareEngineerRepository
    )
    {
        this.softwareEngineerRepository=softwareEngineerRepository;
    }
    public List<SoftwareEngineer> getAllSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    @PostMapping
    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }


    public SoftwareEngineer getAllSoftwareEngineersById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(id+"not found"));
    }
}
