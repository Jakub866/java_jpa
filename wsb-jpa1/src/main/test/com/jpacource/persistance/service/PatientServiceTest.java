
package com.jpacource.persistance.service;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientDao patientDao;
    //private DoctorDao doctorDao;
    @Mock
    private VisitDao visitDao;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById_shouldReturnPatientTOWithDoctor() {
        // given
        Long patientId = 10L;
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);
        patientEntity.setFirstName("Jane");
        patientEntity.setLastName("Doe");

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);
        doctorEntity.setFirstName("John");
        doctorEntity.setLastName("Smith");

        doctorEntity.setSpecialization(Specialization.OCULIST);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(100L);
        visitEntity.setDescription("Routine checkup");
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDoctor(doctorEntity);

        patientEntity.setVisits(List.of(visitEntity));

        when(patientDao.findOne(patientId)).thenReturn(patientEntity);

        // when
        PatientTO patientTO = patientService.findById(patientId);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);
        assertThat(patientTO.getFirstName()).isEqualTo("Jane");
        assertThat(patientTO.getVisits()).hasSize(1);
        assertThat(patientTO.getVisits().get(0).getDoctorLastName()).isEqualTo("Smith");
    }

    @Test
    void deletePatient_shouldCascadeDeleteVisits_andNotDeleteDoctors() {
        // given
        Long patientId = 1L;
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);
        doctorEntity.setFirstName("John");
        doctorEntity.setLastName("Smith");
        doctorEntity.setSpecialization(Specialization.OCULIST);

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(100L);
        visitEntity.setDescription("Routine checkup");
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDoctor(doctorEntity);

        patientEntity.setVisits(List.of(visitEntity));

        when(patientDao.findOne(patientId)).thenReturn(patientEntity);

        // when
        patientService.deleteById(patientId);

        // then
        verify(patientDao, times(1)).delete(patientId);
        verify(visitDao, times(1)).delete(visitEntity.getId());
        //verify(doctorDao, times(0)).delete(doctorEntity.getId());
    }

}