package designtest.striim.genericfilereader.model;

public class UserInput {
	private String inputString;
	private String filePath;
	
	private EncryptionType encryptionType;
	private CompressionType compressionType;
	private EncodingType encodingType;
	private FileType fileType;
	private PrintOption printOption;
	
	public String getInputString() {
		return inputString;
	}
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public EncryptionType getEncryptionType() {
		return encryptionType;
	}
	public void setEncryptionType(EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}
	public CompressionType getCompressionType() {
		return compressionType;
	}
	public void setCompressionType(CompressionType compressionType) {
		this.compressionType = compressionType;
	}
	public EncodingType getEncodingType() {
		return encodingType;
	}
	public void setEncodingType(EncodingType encodingType) {
		this.encodingType = encodingType;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public PrintOption getPrintOption() {
		return printOption;
	}
	public void setPrintOption(PrintOption printOption) {
		this.printOption = printOption;
	}
}
