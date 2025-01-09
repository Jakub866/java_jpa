package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PatientController {

    private final PatientService patientService;
    private final VisitService visitService;

    public PatientController(PatientService patientService, VisitService visitService){this.patientService = patientService;
        this.visitService = visitService;
    }

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
    public List<PatientTO> getPatientsByLastName(@RequestParam String lastName) {
        return patientService.findPatientsByLastName(lastName);
    }

    @GetMapping("/patientsvisits/{id}")
    public List<VisitTO> getPatientsVisits(@PathVariable Long id) {
        return visitService.findPatientsVisits(id);
    }

    @GetMapping("/patientswithmorethanxvisits")
    public List<PatientTO> getPatientsWithMoreThanXVisits(@RequestParam int x) {
        return patientService.findPatientsWithMoreThanXVisits(x);
    }

    @GetMapping("/patientsbygender")
    public List<PatientTO> getPatientsByGenderContaining(@RequestParam String keyword) {
        return patientService.findIfGenderContainsAKeyWord(keyword);
    }

}
