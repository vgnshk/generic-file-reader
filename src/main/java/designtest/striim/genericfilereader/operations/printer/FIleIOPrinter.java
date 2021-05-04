package designtest.striim.genericfilereader.operations.printer;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;

public class FIleIOPrinter extends PrintOperation{

    public FIleIOPrinter() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Data process(Data incoming) {
        return processNext(incoming);
    }

    @Override
    public void setStatus(Data data) {
        data.setDataReadStatus(DataReadStatus.PRINTED);
    }
}
