# generic-file-reader
Generic file based reader that loads components based on user input. Supports decompression, decoding, decryption, and parsing of file content.

## How it works
Users can input the command to read a file along with the properties of the file. See sample usages below.

#### Usage 1
```Python
>> readCSVRecord  file=foo.zzz  compression=gzip encryption=AES128
```
File will be decompressed, decrypted, and parsed as CSV. Data will be displayed in std_out.
 
#### Usage 2
```bash
>> readCSVRecord  file=foo.zzz  compression=gzip 
```
File will be decompressed, and parsed as CSV. Data will be displayed in std_out.
 
#### Usage 3
```bash
>> readCSVRecord  file=foo.zzz  encryption=AES128
```
File will be decrypted, and parsed as CSV. Data will be displayed in std_out.
 
#### Usage 4
```bash
>> readCSVRecord  file=foo.zzz  
```
File will be parsed as CSV. Data will be displayed in std_out.
 
#### Usage 5
```bash
>> readXMLRecord  file=foo.zzz  
```
File will be parsed as XML. Data will be displayed in std_out.
 
#### Usage 6
```bash
>> readCSVRecord  file=foo.zzz  encryption=GPG output=out.csv
```
File will be decrypted, and parsed as CSV. Data will be written out.csv
 
 
## Supported features
Following features are supported by the application.
* Decompression - zip, gzip, tar
* Decryption - AES, GPG
* Decoding - ISO-8859-1, ISO-646, UTF-8 (default)
* Parsing - CSV (default), XML, DAT
* Output - Std_out (default), file
 
 
## Classes and Design
#### Data
* Data is the encapsulation of the file data that is being processed.
* The class has a ‘data’ field that will store the result of the data after every operation such as decompression, decryption, etc.
* The class also has a ‘status’ enum which specifies the status of the user request. At the end of each operation, the status is set accordingly.

#### Application
* The application opens a std_in and waits for the user to input the file read command along with properties.
* The input gets parsed and an object of UserInput class is created.
 
#### UserInputHandler
* The UserInput object created by the application is provided to a handler to process.
* This class does the **defaulting** of operation types if not specified by the users.
  * Default encoding = UTF-8
  * Default file type = CSV
  * Default output = STD_OUT
 
#### UserInputValidator
* The handler uses a UserInputValidator to validate the user input.
* The validator validates the following.
  * Valid filename
  * Has access to file
  * Supported compression type
  * Supported encryption type
  * Supported encoding type
  * Supported parsing type
  * Supported output option
* If any of the above validation fails, an InvalidUserInputException is thrown. On successful validation, the input is passed for further processing.
 
#### Operation
* Operation is the fundamental class for all operations done by the application.
* Operation is an **abstract class** with an abstract process() method. The method accepts and returns an object of 'Data'. The data object travels between the different operations.
* All operations such as _decompression, decryption, decoding, parsing, and printing will extend_ the Operation class.
* Pattern - **Chain of responsibility**.
* The class also has a ‘next’ reference for the next operation in line, a ‘processNext()’ method which will be called by the running operation to trigger the next operation.
* An abstract method ‘setStatus()’ will be defined by all concrete operation classes which sets the status of the operation completed. 
* Status will be one of, DECOMPRESSED, DECRYPTED, DECODED, PARSED, PRINTED, FAILED, SUSPENDED.
 
#### OperationsFactory
* A factory class OperationsFactory creates the concrete class objects of Operation for a given property type.
* The class has the following **static** methods.
  * getDecompressionOperation(enum CompressionType): Operation
  * getDecryptionOperation(enum EncryptionType): Operation
  * getDecodingOperation(enum EncodingType): Operation
  * getFileParseOperation(enum FileType): Operation
  * getPrintOperation(enum PrintOption): Operation
* The enumerations for the different types of operations will have the supported compression, encryption, encoding, file, and print types.
* The caller of the static methods will provide a supported type of an operation to get an object of the respective operation.
 
#### OperationsBuilder
* A builder class OperationsBuilder builds the chain of operations based on the user input.
* The class has the following methods.
  * withCompression(enum CompressionType): OperationsBuilder
  * withEncryption(enum EncryptionType): OperationsBuilder
  * withEncoding(enum EncodingType): OperationsBuilder
  * withFileType(enum FileType): OperationsBuilder
  * withPrintOption(enum PrintOption): OperationsBuilder
  * build(): Operation
 
* The individual ‘with’ methods help build the builder with appropriate operation objects, and the build() method creates the chain of operations, and returns the reference of the first operation to the caller.
  
## Design Justification
#### Builder 
* The builder pattern helps in building the required operations object purely based on the user input. 
* If a particular operation is not required for the user input, then the respective operation class will not be instantiated / used for the request.
* When a **new type of operation** is added for support in the future, we can easily integrate with the software by **updating the builder** with appropriate methods, and changes to the build order. The existing users of the builder will not be affected in such cases, and users can seamlessly start using the newly added operations.
 
#### Chain of responsibility
* Chain of responsibility pattern helps in creating a chain of Operation objects based on the user input.
* Only the required operations can be created and chained for a sequence of operations.
* All the operations use a ‘Data’ object which stores the data that is getting processed, along with the status of the operation.
* Each operation will update the processed data in the object and pass it around for the subsequent operations to consume and process. Upon completion, each operation updates the status in the data object.
* When a **new type of operation** is added for support in the future, we only have to **create a new concrete implementation** extending the Operation and define the process() and setStatus() methods.
* The implementation details like the libraries used for the actual operation will be known only to that particular operation subclass, and is independent of other operations behavior. Thus, each operation implementation can be individually maintained.
* **Unit testing** the concrete classes is simple. The chaining of different classes can be unit tested by mocking the objects.
 
#### Factory
* The OperationFactory is solely responsible for the **instantiation** of respective operation classes based on the type provided by the user.
* By abstracting the concrete class instantiations from the users, we can easily maintain the implementations.
* New operation types in the future can be added by updating the factory with the respective class details.
* For simplicity, a single factory is created to do all the object creations.
* Abstract Factory? We can also make this an _Abstract Factory_ and create individual factories for each operation type, but that will increase the maintenance.
* Dependency Injection? The factory can also be replaced by _Dependency Injection_, so that the builder is independent of the factory, and the required concrete class objects will be injected by an injector. But that will again increase the cost of **maintenance**. One advantage of Dependency Injection will be unit testing the builder, but that is already enabled using mocks.
* The factory methods are made **static**, so we can easily mock the methods during unit testing the users of the factory, in this case, the OperationsBuilder. 
 
## Assumptions
* Validation - An InvalidUserInputException is created when the user input is invalid. The error message is displayed in the command prompt.
* Decryption - The decryption process will prompt the user to type the **password**, and the same will be accepted from std_in.
* Decompression - When the compressed file contains **more than 1 file**, then the application will print only the file names. This will ignore the file parsing type provided by the user.
* File Types - The application supports parsing of CSV, XML, and DAT files. The user input is parsed and the file type is identified. The default file type is **CSV**.
* Print Option - The data read from the file after all operations is written to the output option specified by the user. STD_OUT and FILE_OUT are supported. If the user does not specify an option, this is defaulted to **STD_OUT**.
* Error Scenarios - The application will create exceptions and print error messages in the screen in the event of following scenarios.
  * No appropriate access on the file / unknown file.
  * Compressed file contains more than 1 file, and further operations are performed on it (parsing option provided by the user will be ignored).
  * Incompatibility between different operations.
  * Incorrect password for decryption.
  * Incorrect file type from input. Ex., CSV file asked to be parsed as XML.
 
 
## Testing
* Unit test cases are created for the concrete operation classes.
* **Positive and negative** test cases are created for the UserInputHandler. Validation exceptions are expected and asserted.
* Factory methods are **mocked** and tested.
* Builder is unit tested for the correct chaining of Operations.
