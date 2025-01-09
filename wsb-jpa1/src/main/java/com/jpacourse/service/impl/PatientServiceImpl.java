package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;
    private final VisitDao visitDao;


    @Override
    public List<PatientTO> findPatientsByLastName(String lastName) {
        List<PatientEntity> patients = patientDao.findByLastName(lastName);

        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, VisitDao visitDao)
    {
        this.visitDao = visitDao;
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    public void deleteById(long id) {
        final PatientEntity patient = patientDao.findOne(id);

        if (patient != null) {

            List<VisitEntity> visits = patient.getVisits();
            if (visits != null) {
                for (VisitEntity visit : visits) {
                    visitDao.delete(visit.getId());
                }
            }

            patientDao.delete(id);
        }
    }

    @Override
    public List<PatientTO> findPatientsWithMoreThanXVisits(int x) {
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(x);

        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findIfGenderContainsAKeyWord(String keyWord) {
        List<PatientEntity> patients = patientDao.findIfGenderContainsAKeyWord(keyWord);

        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

}

