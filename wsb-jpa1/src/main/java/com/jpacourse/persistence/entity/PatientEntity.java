package com.jpacourse.persistence.entity;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.enums.Gender;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private long telephoneNumber;

	private String email;

	@Column(nullable = false)
	private long patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	//Relacja jednostronna: rodzic wie o dziecku. Encja doktora zna adres
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", nullable = true)
	private AddressEntity address;

	//Relacja jednostronna: rodzic wie o dziecku. Encja pacjent zna przypisane mu wizyty
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> visits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(long patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public void setAddress(AddressEntity addressEntity) {this.address = addressEntity;}

	public AddressEntity getAddress() {return this.address;}

	public void setVisits(List<VisitEntity> visitEntityList) {this.visits = visitEntityList;}

	public List<VisitEntity> getVisits() {return this.visits;}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
