package designtest.striim.genericfilereader.validator;

import designtest.striim.genericfilereader.model.UserInput;

/**
 * Validates the following
 * 1. File exists
 * 2. User has file access
 * 3. Is compression-type supported
 * 4. Is encryption-type supported
 * 5. Is encoding-type supported
 * 6. Is file-type supported
 */
public class UserInputValidator {

	public boolean validate(UserInput userInput) {
		if(isValidFile(userInput) && hasAccess(userInput) && isSupportedCompression(userInput) && 
				isSupportedEncryption(userInput) && isSupportedEncoding(userInput) && 
				isSupportedFileType(userInput)) {
			/*
			 * Log validation successful
			 */
			return true;
		}
		else {
			/*
			 * Validation failure
			 */
			return false;
		}
	}

	private boolean isSupportedEncoding(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isSupportedEncryption(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean hasAccess(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isValidFile(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isSupportedFileType(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isSupportedCompression(UserInput userInput) {
		// TODO Auto-generated method stub
		return false;
	}
}
