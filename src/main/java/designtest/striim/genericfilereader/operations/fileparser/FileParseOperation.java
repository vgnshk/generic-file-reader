package designtest.striim.genericfilereader.operations.fileparser;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.operations.Operation;

/**
 * File parse operation implementations will extend this class and define the abstract methods
 */
public abstract class FileParseOperation extends Operation {
	protected Data simpleFileRead(Data incoming) {
		/*
		 * Read filePath in incoming and store the content in 'data' 
		 */
		return new Data();
	}
}
