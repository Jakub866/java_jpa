package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.jdbc.Sql;
import com.jpacourse.persistence.enums.Gender;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
@Transactional
@ComponentScan(basePackages = "com.jpacourse.persistence")
class Lab03PatientDaoTest {

    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;



    @BeforeEach
    void setUp() {
        DoctorEntity doctor1 = new DoctorEntity();
        doctor1.setFirstName("John");
        doctor1.setLastName("Smith");
        doctor1.setDoctorNumber(123456L);
        doctor1.setTelephoneNumber(123456L);
        doctor1.setSpecialization(Specialization.OCULIST);
        doctorDao.save(doctor1);

        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("Jane");
        patient1.setLastName("Doe");
        patient1.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient1.setGender(Gender.MALE);


        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        patient2.setDateOfBirth(LocalDate.of(2000, 1, 1));

        patient2.setGender(Gender.FEMALE);

        VisitEntity visit1 = new VisitEntity();
        visit1.setPatient(patient1);
        visit1.setTime(LocalDateTime.now());
        visit1.setDescription("Sprawdzenie stanu zdrowia");
        visit1.setDoctor(doctor1);

        VisitEntity visit2 = new VisitEntity();
        visit2.setPatient(patient1);
        visit2.setTime(LocalDateTime.now());
        visit2.setDescription("Wizyta kontrolna");
        visit2.setDoctor(doctor1);

        VisitEntity visit3 = new VisitEntity();
        visit3.setPatient(patient1);
        visit3.setTime(LocalDateTime.now());
        visit3.setDescription("Wizyta kontrolna 2");
        visit3.setDoctor(doctor1);


        patient1.setVisits(List.of(visit1, visit2, visit3));
        patient2.setVisits(List.of());

        patientDao.save(patient1);
        patientDao.save(patient2);


    }

    @Test
    void findByLastName_shouldReturnPatientsWithGivenLastName() {
        // Given
        String lastName = "Doe";

        // When
        List<PatientEntity> patients = patientDao.findByLastName(lastName);

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.getLastName().equals(lastName));
    }

    @Test
    void findPatientsWithMoreThanXVisits_shouldReturnPatientsWithMoreThanXVisits() {
        // Given
        int x = 2;

        // When
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(x);

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.getVisits().size() > x);
    }

    @Test
    void findPatientsByGenderContaining_shouldReturnPatientsWithGenderContainingKeyword() {
        // Given
        String keyword = "FEMALE";

        // When
        List<PatientEntity> patients = patientDao.findIfGenderContainsAKeyWord(keyword);

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.getGender().toString().contains(keyword));
    }



}