package designtest.striim.genericfilereader;

import designtest.striim.genericfilereader.exception.DataReadException;
import designtest.striim.genericfilereader.exception.InvalidUserInputException;
import designtest.striim.genericfilereader.model.UserInput;

public class GenericFileReaderApplication {

	public static void main(String[] args) {

		UserInputHandler inputHandler = new UserInputHandler();
		
		while(true) {
			/*
			 * Input scanner is on
			 * Wait for user input - single line
			 * After a line is typed, the input command is parsed and provided to UserInputHandler
			 * If user says 'exit', the loop is broken and application stops
			 */
			UserInput input = new UserInput();
			try {
				boolean status = inputHandler.handleUserInput(input);
				if(!status){
					/*
					Print unsuccessful message
					 */
				}
			} catch (InvalidUserInputException | DataReadException e) {
				e.printStackTrace();
			}
		}
	}

}
