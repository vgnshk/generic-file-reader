package designtest.striim.genericfilereader.operations;

import designtest.striim.genericfilereader.model.*;
import designtest.striim.genericfilereader.operations.decoder.Iso646Decoding;
import designtest.striim.genericfilereader.operations.decoder.Iso8859Decoding;
import designtest.striim.genericfilereader.operations.decoder.Utf8Decoding;
import designtest.striim.genericfilereader.operations.decompressor.DecompressionOperation;
import designtest.striim.genericfilereader.operations.decompressor.GzipDecompression;
import designtest.striim.genericfilereader.operations.decompressor.TarDecompression;
import designtest.striim.genericfilereader.operations.decompressor.ZipDecompression;
import designtest.striim.genericfilereader.operations.decryptor.AesDecryption;
import designtest.striim.genericfilereader.operations.decryptor.GpgDecryption;
import designtest.striim.genericfilereader.operations.factory.OperationsFactory;
import designtest.striim.genericfilereader.operations.fileparser.CsvFileParser;
import designtest.striim.genericfilereader.operations.fileparser.DatFileParser;
import designtest.striim.genericfilereader.operations.fileparser.XmlFileParser;
import designtest.striim.genericfilereader.operations.printer.StandardIOPrinter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OperationsBuilderTest {

    static Data decompressedData = new Data();
    static Data decodedData = new Data();
    static Data decryptedData = new Data();
    static Data parsedData = new Data();
    static Data printedData = new Data();

    static Operation decompressionOperation;
    static Operation decodingOperation;
    static Operation decryptionOperation;
    static Operation parsingOperation;
    static Operation printingOperation;

    @BeforeClass
    public static void init(){
        /*
        Setting up mock data
         */
        decompressedData.setDataReadStatus(DataReadStatus.DECOMPRESSED);
        decodedData.setDataReadStatus(DataReadStatus.DECODED);
        decryptedData.setDataReadStatus(DataReadStatus.DECRYPTED);
        parsedData.setDataReadStatus(DataReadStatus.PARSED);
        printedData.setDataReadStatus(DataReadStatus.PRINTED);

        /*
        Setting up mock operations
         */
        decompressionOperation = Mockito.mock(ZipDecompression.class);
        decodingOperation = Mockito.mock(Utf8Decoding.class);
        decryptionOperation = Mockito.mock(AesDecryption.class);
        parsingOperation = Mockito.mock(CsvFileParser.class);
        printingOperation = Mockito.mock(StandardIOPrinter.class);

        doCallRealMethod().when(decompressionOperation).setStatus(any());
        doCallRealMethod().when(decodingOperation).setStatus(any());
        doCallRealMethod().when(decryptionOperation).setStatus(any());
        doCallRealMethod().when(parsingOperation).setStatus(any());
        doCallRealMethod().when(printingOperation).setStatus(any());

        when(decompressionOperation.processNext(any())).thenCallRealMethod();
        when(decodingOperation.processNext(any())).thenCallRealMethod();
        when(decryptionOperation.processNext(any())).thenCallRealMethod();
        when(parsingOperation.processNext(any())).thenCallRealMethod();
        when(printingOperation.processNext(any())).thenCallRealMethod();

        when(decompressionOperation.process(any())).thenReturn(decompressedData);
        when(decodingOperation.process(any())).thenReturn(decodedData);
        when(decryptionOperation.process(any())).thenReturn(decryptedData);
        when(parsingOperation.process(any())).thenReturn(parsedData);
        when(printingOperation.process(any())).thenReturn(printedData);

        when(decompressionOperation.addNext(any())).thenCallRealMethod();
        when(decodingOperation.addNext(any())).thenCallRealMethod();
        when(decryptionOperation.addNext(any())).thenCallRealMethod();
        when(parsingOperation.addNext(any())).thenCallRealMethod();
        when(printingOperation.addNext(any())).thenCallRealMethod();

        /*
        Mocking static methods of OperationsFactory
         */
        MockedStatic<OperationsFactory> factoryMockedStatic = Mockito.mockStatic(OperationsFactory.class);
        factoryMockedStatic.when(() -> OperationsFactory.getDecodingOperation(any())).thenReturn(decodingOperation);
        factoryMockedStatic.when(() -> OperationsFactory.getDecompressionOperation(any())).thenReturn(decompressionOperation);
        factoryMockedStatic.when(() -> OperationsFactory.getDecryptionOperation(any())).thenReturn(decryptionOperation);
        factoryMockedStatic.when(() -> OperationsFactory.getFileParseOperation(any())).thenReturn(parsingOperation);
        factoryMockedStatic.when(() -> OperationsFactory.getPrintOperation(any())).thenReturn(printingOperation);

    }

    @Test
    public void zipCompressionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.ZIP)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECOMPRESSED, processed.getDataReadStatus());
    }
    @Test
    public void gzipCompressionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.GZIP)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECOMPRESSED, processed.getDataReadStatus());
    }
    @Test
    public void tarCompressionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.TAR)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECOMPRESSED, processed.getDataReadStatus());
    }
    @Test
    public void iso646EncodingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withEncoding(EncodingType.ISO_646)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
    }
    @Test
    public void iso8859EncodingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withEncoding(EncodingType.ISO_8859_1)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
    }
    @Test
    public void utf8EncodingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withEncoding(EncodingType.UTF_8)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECODED, processed.getDataReadStatus());
    }
    @Test
    public void aesEncryptionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withEncryption(EncryptionType.AES)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECRYPTED, processed.getDataReadStatus());
    }
    @Test
    public void gpgEncryptionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withEncryption(EncryptionType.GPG)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.DECRYPTED, processed.getDataReadStatus());
    }
    @Test
    public void csvParsingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withFileParser(FileType.CSV)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.PARSED, processed.getDataReadStatus());
    }
    @Test
    public void xmlParsingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withFileParser(FileType.XML)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.PARSED, processed.getDataReadStatus());
    }
    @Test
    public void datParsingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withFileParser(FileType.DAT)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertEquals(DataReadStatus.PARSED, processed.getDataReadStatus());
    }
    @Test
    public void gzipCompressionAndCsvParsingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withFileParser(FileType.DAT)
                .withCompression(CompressionType.GZIP)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }

    @Test
    public void gzipCompressionAndUtf8EncodingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.GZIP)
                .withEncoding(EncodingType.UTF_8)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }
    @Test
    public void zipCompressionAndAesEncryptionBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.ZIP)
                .withEncryption(EncryptionType.AES)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }
    @Test
    public void zipCompressionAndAesEncryptionAndUtf8EncodingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.ZIP)
                .withEncryption(EncryptionType.AES)
                .withEncoding(EncodingType.UTF_8)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }
    @Test
    public void zipCompressionAndAesEncryptionAndUtf8EncodingAndCsvParsingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.ZIP)
                .withEncryption(EncryptionType.AES)
                .withEncoding(EncodingType.UTF_8)
                .withFileParser(FileType.CSV)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }
    @Test
    public void zipCompressionAndAesEncryptionAndUtf8EncodingAndCsvParsingAndFileIoPrintingBuildTest(){
        Operation operation = new OperationsBuilder()
                .withCompression(CompressionType.ZIP)
                .withEncryption(EncryptionType.AES)
                .withEncoding(EncodingType.UTF_8)
                .withFileParser(FileType.CSV)
                .withPrintOption(PrintOption.FILE_IO)
                .build();
        Assert.assertNotNull(operation);
        Data processed = operation.process(new Data());

        Assert.assertNotNull(processed);
    }
}