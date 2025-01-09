package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class Lab03PatientLockTest {

    @Autowired
    private PatientDao patientDao;


    @Test
    public void shouldThrowOptimisticLockingException() {
        // given
        long patientId = 1L;
        PatientEntity patientT1 = patientDao.findOne(patientId);

        // when
        patientT1.setFirstName("Pierwsze imie");
        patientDao.update(patientT1);

        // then
        assertThatThrownBy(() -> {
            patientT1.setFirstName("Zmiana imienia");
            patientDao.update(patientT1);
        }).isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
}