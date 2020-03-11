package medicalimagebrowser.entity;

//import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Series implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Study study;

    @ToString.Exclude
    @OneToMany(mappedBy = "series")
    private List<Instance> instances;

    @Column(unique = true)
    private String seriesInstanceUID;

    private String seriesNumber;

    private String seriesDescription;

    private String stationName;

    private String numberOfTemporalPositions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public String getSeriesInstanceUID() {
		return seriesInstanceUID;
	}

	public void setSeriesInstanceUID(String seriesInstanceUID) {
		this.seriesInstanceUID = seriesInstanceUID;
	}

	public String getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public String getSeriesDescription() {
		return seriesDescription;
	}

	public void setSeriesDescription(String seriesDescription) {
		this.seriesDescription = seriesDescription;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getNumberOfTemporalPositions() {
		return numberOfTemporalPositions;
	}

	public void setNumberOfTemporalPositions(String numberOfTemporalPositions) {
		this.numberOfTemporalPositions = numberOfTemporalPositions;
	}

	@Override
	public String toString() {
		return "Series [id=" + id + ", study=" + study + ", instances=" + instances + ", seriesInstanceUID="
				+ seriesInstanceUID + ", seriesNumber=" + seriesNumber + ", seriesDescription=" + seriesDescription
				+ ", stationName=" + stationName + ", numberOfTemporalPositions=" + numberOfTemporalPositions + "]";
	}

    
}
