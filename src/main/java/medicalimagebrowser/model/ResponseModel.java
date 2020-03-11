package medicalimagebrowser.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


public class ResponseModel<T> implements Serializable {

    private Integer total;

    private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ResponseModel [total=" + total + ", rows=" + rows + "]";
	}

    
}
