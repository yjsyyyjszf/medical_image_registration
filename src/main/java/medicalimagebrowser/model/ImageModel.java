package medicalimagebrowser.model;

import lombok.Data;

import java.io.Serializable;


public class ImageModel implements Serializable {

    private Integer rows;

    private Integer columns;

    private Double windowWidth;

    private Double windowCenter;

    private String base64PixelData;

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

	public String getBase64PixelData() {
		return base64PixelData;
	}

	public void setBase64PixelData(String base64PixelData) {
		this.base64PixelData = base64PixelData;
	}

	@Override
	public String toString() {
		return "ImageModel [rows=" + rows + ", columns=" + columns + ", windowWidth=" + windowWidth + ", windowCenter="
				+ windowCenter + ", base64PixelData=" + base64PixelData + "]";
	}

    
}
