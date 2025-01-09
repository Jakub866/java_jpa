package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){this.patientService = patientService; }

    @GetMapping("/patient/{id}")
    PatientTO findBaId(@PathVariable final Long id) {
        final PatientTO patient = patientService.findById(id);
        if(patient != null)
        {
            return patient;
        }
        throw new EntityNotFoundException("There is no such id " + id);
    }


    @GetMapping("/patients")
    public List<PatientEntity> getPatientsByLastName(@RequestParam String lastName) {
        return patientService.findPatientsByLastName(lastName);
    }
}
