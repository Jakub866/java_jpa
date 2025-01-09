package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VisitDaoImpl  extends AbstractDao<PatientEntity, Long> implements VisitDao {

    @Override
    public List<VisitEntity> findByPatientId(long id) {
        return entityManager.createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :id", VisitEntity.class)
                .setParameter("id", id)
                .getResultList();
    }


}