package designtest.striim.genericfilereader.operations.decompressor;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;

public class ZipDecompression extends DecompressionOperation {

	public ZipDecompression() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data process(Data incoming) {
		// TODO Auto-generated method stub
		return processNext(incoming);
	}

	@Override
	public void setStatus(Data data) {
		data.setDataReadStatus(DataReadStatus.DECOMPRESSED);
	}
}
