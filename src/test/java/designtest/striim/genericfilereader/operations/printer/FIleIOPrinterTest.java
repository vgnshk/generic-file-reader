package designtest.striim.genericfilereader.operations.printer;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import designtest.striim.genericfilereader.operations.fileparser.CsvFileParser;
import org.junit.Assert;
import org.junit.Test;


public class FIleIOPrinterTest {

    @Test
    public void process() {
        FIleIOPrinter operation = new FIleIOPrinter();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.PRINTED, processed.getDataReadStatus());
    }
}