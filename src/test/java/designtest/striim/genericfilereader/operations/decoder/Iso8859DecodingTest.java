package designtest.striim.genericfilereader.operations.decoder;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class Iso8859DecodingTest {

    @Test
    public void process() {
        Iso8859Decoding operation = new Iso8859Decoding();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
    }
}