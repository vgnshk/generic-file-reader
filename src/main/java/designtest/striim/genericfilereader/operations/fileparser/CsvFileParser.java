package designtest.striim.genericfilereader.operations.fileparser;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;

public class CsvFileParser  extends FileParseOperation {

	public CsvFileParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data process(Data incoming) {
		// TODO Auto-generated method stub
		return processNext(incoming);
	}

	@Override
	public void setStatus(Data data) {
		data.setDataReadStatus(DataReadStatus.PARSED);
	}
}
