package designtest.striim.genericfilereader.operations.factory;

import designtest.striim.genericfilereader.model.*;
import designtest.striim.genericfilereader.operations.Operation;
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

/**
 * Factory responsible for instantiating concrete classes for different operations
 * Static methods in the factory accept the specification of the type of operations and return
 * the respective operation object
 */
public class OperationsFactory {

	private OperationsFactory() {}

	public static Operation getDecompressionOperation(CompressionType compressionType) {
		switch(compressionType){
			case ZIP: return new ZipDecompression();
			case GZIP: return new GzipDecompression();
			case TAR: return new TarDecompression();
		}
		return null;
	}

	public static Operation getDecodingOperation(EncodingType encodingType) {
		switch(encodingType){
			case UTF_8: return new Utf8Decoding();
			case ISO_8859_1: return new Iso8859Decoding();
			case ISO_646: return new Iso646Decoding();
		}
		return null;
	}

	public static Operation getDecryptionOperation(EncryptionType encryptionType) {
		switch(encryptionType){
			case AES: return new AesDecryption();
			case GPG: return new GpgDecryption();
		}
		return null;
	}

	public static Operation getFileParseOperation(FileType fileType) {
		switch (fileType){
			case CSV: return new CsvFileParser();
			case DAT: return new DatFileParser();
			case XML: return new XmlFileParser();
		}
		return null;
	}

	public static Operation getPrintOperation(PrintOption printOption) {
		switch(printOption){
			case STD_IO: return new StandardIOPrinter();
			case FILE_IO: return new FIleIOPrinter();
		}
		return null;
	}

}
