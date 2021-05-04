package designtest.striim.genericfilereader.operations.decompressor;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;

public class GzipDecompressionTest {

    @Test
    public void process() {
        GzipDecompression operation = new GzipDecompression();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECOMPRESSED, processed.getDataReadStatus());
    }
}