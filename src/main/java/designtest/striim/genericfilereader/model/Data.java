package designtest.striim.genericfilereader.model;

/**
 * Encapsulation of data being processed by the application
 * Stores the data as string and the status of the user read request
 * Any exception during the processing will update the error
 */
public class Data {
	private String filePath;
	private String data;
	private DataReadStatus dataReadStatus;
	private String error;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DataReadStatus getDataReadStatus() {
		return dataReadStatus;
	}

	public void setDataReadStatus(DataReadStatus dataReadStatus) {
		this.dataReadStatus = dataReadStatus;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
