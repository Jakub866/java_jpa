package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.List;

public interface PatientService {

    public PatientTO findById(final Long id);

    List<PatientEntity> findPatientsByLastName(String lastName);
}
