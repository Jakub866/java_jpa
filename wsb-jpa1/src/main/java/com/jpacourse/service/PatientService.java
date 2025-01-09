package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public PatientTO findById(final Long id);

    List<PatientTO> findPatientsByLastName(String lastName);
    List<PatientTO> findPatientsWithMoreThanXVisits(int x);
    List<PatientTO> findIfGenderContainsAKeyWord(String keyWord);
}
