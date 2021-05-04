package designtest.striim.genericfilereader.operations.decryptor;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import designtest.striim.genericfilereader.operations.decompressor.GzipDecompression;
import org.junit.Assert;
import org.junit.Test;

public class AesDecryptionTest {

    @Test
    public void process() {
        AesDecryption operation = new AesDecryption();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECRYPTED, processed.getDataReadStatus());
    }
}