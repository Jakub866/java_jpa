package com.jpacourse.persistence.dao;


import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientDao extends Dao<PatientEntity, Long> {
    void save_visit(long k, long l, LocalDateTime n, String p);
    List<PatientEntity> findByLastName(String lastName);
    List<PatientEntity> findPatientsWithMoreThanXVisits(int x);
    List<PatientEntity> findIfGenderContainsAKeyWord(String keyWord);
}
