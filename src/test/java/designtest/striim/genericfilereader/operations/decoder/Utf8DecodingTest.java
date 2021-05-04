package designtest.striim.genericfilereader.operations.decoder;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class Utf8DecodingTest {

    @Test
    public void process() {
        Utf8Decoding operation = new Utf8Decoding();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
    }
}