package medicalimagebrowser.entity;

//import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Study implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Patient patient;

    @ToString.Exclude
    @OneToMany(mappedBy = "study")
    private List<Series> series;

    @Column(unique = true)
    private String studyID;

    private String studyDate;

    private String studyInstanceUID;

    private String studyDescription;

    private String accessionNumber;

    private String referringPhysicianName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public String getStudyID() {
		return studyID;
	}

	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}

	public String getStudyDescription() {
		return studyDescription;
	}

	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getReferringPhysicianName() {
		return referringPhysicianName;
	}

	public void setReferringPhysicianName(String referringPhysicianName) {
		this.referringPhysicianName = referringPhysicianName;
	}

	@Override
	public String toString() {
		return "Study [id=" + id + ", patient=" + patient + ", series=" + series + ", studyID=" + studyID
				+ ", studyDate=" + studyDate + ", studyInstanceUID=" + studyInstanceUID + ", studyDescription="
				+ studyDescription + ", accessionNumber=" + accessionNumber + ", referringPhysicianName="
				+ referringPhysicianName + "]";
	}

    
}
