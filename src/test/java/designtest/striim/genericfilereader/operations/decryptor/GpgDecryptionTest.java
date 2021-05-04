package designtest.striim.genericfilereader.operations.decryptor;

import designtest.striim.genericfilereader.model.Data;
import designtest.striim.genericfilereader.model.DataReadStatus;
import org.junit.Assert;
import org.junit.Test;

public class GpgDecryptionTest {

    @Test
    public void process() {
        AesDecryption operation = new AesDecryption();
        Data processed = operation.process(new Data());
        Assert.assertEquals(DataReadStatus.DECRYPTED, processed.getDataReadStatus());
    }
}