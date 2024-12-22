package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientDaoTest {

    @Mock
    private PatientDao patientDao;
    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addVisitToPatient_shouldAddVisitAndUpdatePatient() {
        // Given
        Long patientId = 1L;
        Long doctorId = 2L;
        LocalDateTime visitDate = LocalDateTime.now();
        String visitDescription = "Routine checkup";

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);
        patientEntity.setFirstName("Jane");
        patientEntity.setLastName("Doe");

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(doctorId);
        doctorEntity.setFirstName("John");
        doctorEntity.setLastName("Smith");

        when(patientDao.findOne(patientId)).thenReturn(patientEntity);

        // When
        patientDao.save_visit(patientId, doctorId, visitDate, visitDescription);

        // Then
        verify(patientDao, times(1)).save(patientEntity);
        assertNotNull(patientEntity.getVisits());
        assertEquals(1, patientEntity.getVisits().size());
        assertEquals(visitDescription, patientEntity.getVisits().get(0).getDescription());
        assertEquals(doctorId, patientEntity.getVisits().get(0).getDoctor().getId());
    }
}