package designtest.striim.genericfilereader.operations.decoder;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;

public class Iso8859Decoding extends DecodingOperation{

	public Iso8859Decoding() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data process(Data incoming) {
		// TODO Auto-generated method stub
		return processNext(incoming);
	}

	@Override
	public void setStatus(Data data) {
		data.setDataReadStatus(DataReadStatus.DECODED);
	}
}
