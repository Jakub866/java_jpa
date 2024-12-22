package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.Specialization;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private long telephoneNumber;

	private String email;

	@Column(nullable = false)
	private long doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	//Relacja jednostronna: rodzic wie o dziecku. Encja doktora zna adres
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private AddressEntity address;

	//Relacja dwustronna: rodzic wie o dziecku i odwrotnie. Encja doktor zna przypisane mu wizyty a wizyty znaja przypisanego im lekarza
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

	public long getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(long doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public void setAddress(AddressEntity addressEntity) {this.address = addressEntity;}

	public AddressEntity getAddress() {return this.address;}

	public void setVisits(List<VisitEntity> visitEntityList) {this.visits = visitEntityList;}

	public List<VisitEntity> getVisits() {return this.visits;}
}
