package designtest.striim.genericfilereader.operations;

import designtest.striim.genericfilereader.model.*;
import designtest.striim.genericfilereader.operations.factory.OperationsFactory;
import designtest.striim.genericfilereader.operations.decoder.Iso646Decoding;
import designtest.striim.genericfilereader.operations.decoder.Iso8859Decoding;
import designtest.striim.genericfilereader.operations.decoder.Utf8Decoding;
import designtest.striim.genericfilereader.operations.decompressor.GzipDecompression;
import designtest.striim.genericfilereader.operations.decompressor.TarDecompression;
import designtest.striim.genericfilereader.operations.decompressor.ZipDecompression;
import designtest.striim.genericfilereader.operations.decryptor.AesDecryption;
import designtest.striim.genericfilereader.operations.decryptor.GpgDecryption;
import designtest.striim.genericfilereader.operations.fileparser.CsvFileParser;
import designtest.striim.genericfilereader.operations.fileparser.DatFileParser;
import designtest.striim.genericfilereader.operations.fileparser.XmlFileParser;
import designtest.striim.genericfilereader.operations.printer.FIleIOPrinter;
import designtest.striim.genericfilereader.operations.printer.StandardIOPrinter;
import org.junit.Assert;
import org.junit.Test;

public class OperationsFactoryTest {

    @Test
    public void getDecompressionOperationForGzip() {

        Assert.assertEquals(
                "Incorrect decompression operation created",
                GzipDecompression.class,
                OperationsFactory.getDecompressionOperation(CompressionType.GZIP).getClass()
        );

    }

    @Test
    public void getDecompressionOperationForTar() {

        Assert.assertEquals(
                "Incorrect decompression operation created",
                TarDecompression.class,
                OperationsFactory.getDecompressionOperation(CompressionType.TAR).getClass()
        );
    }

    @Test
    public void getDecompressionOperationForZip() {
        Assert.assertEquals(
                "Incorrect decompression operation created",
                ZipDecompression.class,
                OperationsFactory.getDecompressionOperation(CompressionType.ZIP).getClass()
        );

    }

    @Test
    public void getDecodingOperationForIso646() {

        Assert.assertEquals("Incorrect decoding operation created",
                Iso646Decoding.class,
                OperationsFactory.getDecodingOperation(EncodingType.ISO_646).getClass()
        );

    }

    @Test
    public void getDecodingOperationForIso8859() {

        Assert.assertEquals("Incorrect decoding operation created",
                Iso8859Decoding.class,
                OperationsFactory.getDecodingOperation(EncodingType.ISO_8859_1).getClass()
        );

    }

    @Test
    public void getDecodingOperationForUtf8() {
        Assert.assertEquals("Incorrect decoding operation created",
                Utf8Decoding.class,
                OperationsFactory.getDecodingOperation(EncodingType.UTF_8).getClass()
        );

    }

    @Test
    public void getDecryptionOperationForGpg() {

        Assert.assertEquals("Incorrect decryption operation created",
                GpgDecryption.class,
                OperationsFactory.getDecryptionOperation(EncryptionType.GPG).getClass()
        );

    }

    @Test
    public void getDecryptionOperationForAes() {
        Assert.assertEquals("Incorrect decryption operation created",
                AesDecryption.class,
                OperationsFactory.getDecryptionOperation(EncryptionType.AES).getClass()
        );

    }

    @Test
    public void getFileParseOperationForXml() {

        Assert.assertEquals("Incorrect file parsing operation created",
                XmlFileParser.class,
                OperationsFactory.getFileParseOperation(FileType.XML).getClass()
        );

    }

    @Test
    public void getFileParseOperationForDat() {

        Assert.assertEquals("Incorrect file parsing operation created",
                DatFileParser.class,
                OperationsFactory.getFileParseOperation(FileType.DAT).getClass()
        );

    }

    @Test
    public void getFileParseOperationForCsv() {
        Assert.assertEquals("Incorrect file parsing operation created",
                CsvFileParser.class,
                OperationsFactory.getFileParseOperation(FileType.CSV).getClass()
        );

    }

    @Test
    public void getPrintOperationForFileIo() {

        Assert.assertEquals("Incorrect print operation created",
                FIleIOPrinter.class,
                OperationsFactory.getPrintOperation(PrintOption.FILE_IO).getClass()
        );

    }

    @Test
    public void getPrintOperationForStdIo() {
        Assert.assertEquals("Incorrect print operation created",
                StandardIOPrinter.class,
                OperationsFactory.getPrintOperation(PrintOption.STD_IO).getClass()
        );

    }
}