package designtest.striim.genericfilereader.operations.printer;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class StandardIOPrinterTest {

    @Test
    public void process() {
        StandardIOPrinter operation = new StandardIOPrinter();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.PRINTED, processed.getDataReadStatus());
    }
}