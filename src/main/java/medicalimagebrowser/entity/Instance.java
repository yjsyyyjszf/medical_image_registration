package medicalimagebrowser.entity;

//import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Instance implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Series series;

    private String imageIndex;

    private String SOPInstanceUID;

    @Column(name = "image_rows")
    private Integer rows;

    @Column(name = "image_columns")
    private Integer columns;

    private Double windowWidth;

    private Double windowCenter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public String getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(String imageIndex) {
		this.imageIndex = imageIndex;
	}

	public String getSOPInstanceUID() {
		return SOPInstanceUID;
	}

	public void setSOPInstanceUID(String sOPInstanceUID) {
		SOPInstanceUID = sOPInstanceUID;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public Double getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(Double windowWidth) {
		this.windowWidth = windowWidth;
	}

	public Double getWindowCenter() {
		return windowCenter;
	}

	public void setWindowCenter(Double windowCenter) {
		this.windowCenter = windowCenter;
	}

	@Override
	public String toString() {
		return "Instance [id=" + id + ", series=" + series + ", imageIndex=" + imageIndex + ", SOPInstanceUID="
				+ SOPInstanceUID + ", rows=" + rows + ", columns=" + columns + ", windowWidth=" + windowWidth
				+ ", windowCenter=" + windowCenter + "]";
	}

    
}
