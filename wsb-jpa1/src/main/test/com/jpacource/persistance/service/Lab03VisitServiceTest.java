package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lab03VisitServiceTest {
    @Autowired
    private VisitService visitService;

    @Test
    @Transactional
    public void testShouldReturnPatientVisits() {
        // given
        long patientId = 1L;

        // when
        List<VisitTO> patientVisits = visitService.findPatientsVisits(patientId);

        // then
        assertThat(patientVisits).isNotNull();
        assertThat(patientVisits).hasSize(1);
        assertThat(patientVisits.get(0).getDoctorLastName()).isEqualTo("Kowalski");
    }
}