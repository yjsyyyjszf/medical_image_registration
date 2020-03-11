package medicalimagebrowser.model;

import lombok.Data;

import java.io.Serializable;


public class LookupModel implements Serializable {

    String patientID;

    String patientName;

    String accessionNumber;

    String studyDescription;

    String studyDate;

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

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getStudyDescription() {
		return studyDescription;
	}

	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	@Override
	public String toString() {
		return "LookupModel [patientID=" + patientID + ", patientName=" + patientName + ", accessionNumber="
				+ accessionNumber + ", studyDescription=" + studyDescription + ", studyDate=" + studyDate + "]";
	}

    
}
