package designtest.striim.genericfilereader;

import designtest.striim.genericfilereader.exception.DataReadException;
import designtest.striim.genericfilereader.exception.InvalidUserInputException;
import designtest.striim.genericfilereader.model.*;
import designtest.striim.genericfilereader.operations.Operation;
import designtest.striim.genericfilereader.operations.OperationsBuilder;
import designtest.striim.genericfilereader.validator.UserInputValidator;

/**
 * Handler to accept user input and execute the operations
 */
public class UserInputHandler {
	private static final EncodingType DEFAULT_ENCODING = EncodingType.UTF_8;
	private static final PrintOption DEFAULT_PRINT = PrintOption.STD_IO;
	private static final FileType DEFAULT_FILE_TYPE = FileType.CSV;
	private final boolean applyDefaults;
	private UserInputValidator inputValidator;
	
	public UserInputHandler() {
		this.applyDefaults = true;
		init();
	}
	
	public UserInputHandler(boolean applyDefaults) {
		this.applyDefaults = applyDefaults;
		init();
	}

	/*
	Use init method to create objects / services required to handle user input
	 */
	private void init() {
		inputValidator = new UserInputValidator();
	}

	/**
	 * Handles user input. Throws InvalidUserInputException is validation of input fails
	 * Prints parsed data from file after appropriate decompression, decoding, decryption designtest.striim.genericfilereader.operations (as applicable)
	 * Can accept user option to write data to IO - default is std_out
	 * @param input
	 */
	public boolean handleUserInput(UserInput input) throws InvalidUserInputException, DataReadException {
		if(!inputValidator.validate(input)){
			throw new InvalidUserInputException("One or more input parameters are invalid. Please check and retry");
		}

		Operation operation = getOperationToProcess(input);

		Data output = operation.process(getData(input));
		if(DataReadStatus.PRINTED.equals(output.getDataReadStatus())){
			return true;
		}
		if(DataReadStatus.FAILED.equals(output.getDataReadStatus()) || DataReadStatus.SUSPENDED.equals(output.getDataReadStatus())){
			throw new DataReadException("Error occurred during file read. "+output.getError());
		}
		return false;
	}

	private Operation getOperationToProcess(UserInput input) {
		OperationsBuilder operationsBuilder = new OperationsBuilder();

		if(applyDefaults){
			setDefaults(input);
		}
		if(input.getCompressionType()!=null)
			operationsBuilder = operationsBuilder.withCompression(input.getCompressionType());
		if(input.getEncodingType()!=null)
			operationsBuilder = operationsBuilder.withEncoding(input.getEncodingType());
		if(input.getEncryptionType()!=null)
			operationsBuilder = operationsBuilder.withEncryption(input.getEncryptionType());
		if(input.getFileType()!=null)
			operationsBuilder = operationsBuilder.withFileParser(input.getFileType());
		if(input.getPrintOption()!=null)
			operationsBuilder = operationsBuilder.withPrintOption(input.getPrintOption());

		Operation operation = operationsBuilder.build();

		return operation;
	}

	private void setDefaults(UserInput input) {
		if(input.getEncodingType()==null)
			input.setEncodingType(DEFAULT_ENCODING);
		if(input.getPrintOption()==null)
			input.setPrintOption(DEFAULT_PRINT);
		if(input.getFileType()==null)
			input.setFileType(DEFAULT_FILE_TYPE);
	}

	/**
	 * Builds Data object from UserInput
	 * @param input
	 * @return Data for processing
	 */
	private Data getData(UserInput input) {
		// TODO Auto-generated method stub
		return null;
	}
}
