package designtest.striim.genericfilereader.operations.fileparser;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class CsvFileParserTest {

    @Test
    public void process() {
        CsvFileParser operation = new CsvFileParser();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.PARSED, processed.getDataReadStatus());
    }
}