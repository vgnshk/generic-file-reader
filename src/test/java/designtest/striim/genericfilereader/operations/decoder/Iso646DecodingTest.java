package designtest.striim.genericfilereader.operations.decoder;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;


public class Iso646DecodingTest {

    @Test
    public void process() {
        Iso646Decoding operation = new Iso646Decoding();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
        /*
        Can add other tests for data based on the concrete implementation
         */
    }
}