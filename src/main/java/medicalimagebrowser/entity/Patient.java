package medicalimagebrowser.entity;

//import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Patient implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient")
    private List<Study> studies;

    @Column(unique = true)
    private String patientID;

    private String patientName;

    private String patientBirthDate;

    private String patientSex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientBirthDate() {
		return patientBirthDate;
	}

	public void setPatientBirthDate(String patientBirthDate) {
		this.patientBirthDate = patientBirthDate;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", studies=" + studies + ", patientID=" + patientID + ", patientName="
				+ patientName + ", patientBirthDate=" + patientBirthDate + ", patientSex=" + patientSex + "]";
	}

    
}
