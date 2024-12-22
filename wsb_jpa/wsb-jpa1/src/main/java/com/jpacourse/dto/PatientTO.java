package com.jpacourse.dto;

import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PatientTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    private List<VisitTO> visits;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public LocalDate getBirthDate() {return birthDate;}

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public List<VisitTO> getVisits() {return visits;}

    public void setVisits(List<VisitTO> visits) {this.visits = visits;}
    public void setGender(Gender gender) {this.gender = gender;}
    public Gender getGender() {return this.gender;}
}



