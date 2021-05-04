package designtest.striim.genericfilereader.operations.fileparser;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class XmlFileParserTest {

    @Test
    public void process() {
        XmlFileParser operation = new XmlFileParser();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.PARSED, processed.getDataReadStatus());
    }
}