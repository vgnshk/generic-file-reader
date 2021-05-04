package designtest.striim.genericfilereader.operations;

import designtest.striim.genericfilereader.model.*;
import designtest.striim.genericfilereader.operations.factory.OperationsFactory;

/**
 * Builder to build the different operations based on the user input
 * The sequencing of the operations is handled by the builder
 */
public class OperationsBuilder {
	private Operation initOperation;
	private Operation decompressionOperation;
	private Operation decryptionOperation;
	private Operation decodingOperation;
	private Operation fileReadOperation;
	private Operation printOperation;
	
	private Operation nextOperation;

	public OperationsBuilder() {
	}
	
	public OperationsBuilder withCompression(CompressionType compression) {
		this.decompressionOperation = OperationsFactory.getDecompressionOperation(compression);
		return this;
	}

	public OperationsBuilder withEncoding(EncodingType encoding) {
		this.decodingOperation = OperationsFactory.getDecodingOperation(encoding);
		return this;
	}

	public OperationsBuilder withEncryption(EncryptionType encryption) {
		this.decryptionOperation = OperationsFactory.getDecryptionOperation(encryption);
		return this;
	}

	public OperationsBuilder withFileParser(FileType fileType) {
		this.fileReadOperation = OperationsFactory.getFileParseOperation(fileType);
		return this;
	}

	public OperationsBuilder withPrintOption(PrintOption printOption) {
		this.printOperation = OperationsFactory.getPrintOperation(printOption);
		return this;
	}

	/**
	 * Operations are chained based on the user read request
	 * Following is the order in which the Operations (if required) are chained
	 * 	1. Decompression
	 * 	2. Decryption
	 * 	3. Decoding
	 * 	4. File parsing
	 * 	5. Printing
	 * @return first operation to be executed
	 */
	public Operation build() {
		if(decompressionOperation!=null) {
			initOperation = decompressionOperation;
			nextOperation = decompressionOperation;
		}
		if(decryptionOperation!=null) {
			if(initOperation==null) initOperation = decryptionOperation;
			if(nextOperation==null) nextOperation = decryptionOperation;
			else nextOperation = nextOperation.addNext(decryptionOperation);
		}
		if(decodingOperation!=null) {
			if(initOperation==null) initOperation = decodingOperation;
			if(nextOperation==null) nextOperation = decodingOperation;
			else nextOperation = nextOperation.addNext(decodingOperation);
		}
		if(fileReadOperation!=null) {
			if(initOperation==null) initOperation = fileReadOperation;
			if(nextOperation==null) nextOperation = fileReadOperation;
			else nextOperation = nextOperation.addNext(fileReadOperation);
		}
		if(printOperation!=null) {
			if(initOperation==null) initOperation = printOperation;
			if(nextOperation==null) nextOperation = printOperation;
			else nextOperation = nextOperation.addNext(printOperation);
		}
		return initOperation;
	}

}
