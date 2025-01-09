package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VisitService {
    List<VisitTO> findPatientsVisits(Long id);
}
