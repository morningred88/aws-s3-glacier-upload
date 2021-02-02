# aws-s3-glacier-upload

This project is using high-level API to upload small or large archive files to aws s3 glacier. The archive to upload can be a single file or a zipped folder. 

## AWS SDKs

```bash
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>bom</artifactId>
        <version>2.13.50</version>
        <type>pom</type>
    </dependency>
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-glacier</artifactId>
        <version>1.11.817</version>
    </dependency>
```

## Two Uploading Options

Master: A Restful endpoint -- upload archive to the specific glacier vault
Batch branch: A batch job -- can upload one or many archives to the given vault

### Restful endpoint information

url: http://localhost:8080/aws-s3-glacier-upload/archive
Sample request in JSON:
```bash
{
	"archiveFilePath": "/users/morningred88/test.zip" ,
	"vaultName": "firstvault"
}
```
Archive ID as response, here is a sample response:
```bash
Archive ID: JW4Otxmnp_cUvnAKBNsOk24KCfbBBTbmiP1sDcuDqb-w7kvLT3J3m8-tIwnG3e8tj4ROQY3Wh7zqLUXsBVSwBqMiHD4A5LPrM8bJ-WyLZh5tODJeD55VCoOGqO-zP2I-SvDfwwGQrg
```
### Batch information
Build and run locally, under the project directory

Build locally: 
```bash
mvn -e clean package
```
Run locally:
```bash
Run: java -jar target/aws-s3-glacier-upload.jar C:/users/morningred88/testfile1 C:/users/morningred88/testfile2
```
Sample logging after running:
```bash
2021-02-01 21:31:07 DEBUG upload.App (App.java:29) - Start to upload file C:/users/morningred88/testfile1
2021-02-01 21:31:09 INFO  Service.ArchiveUploadService (ArchiveUploadService.java:57) - C:/users/morningred88/testfile1 has been successfully uploaded. Archive ID is :: mTV2SONhDIOZPAXvOUrO-Ms8IX42bSvsDjDtBclaMDAsfBsYqygwh_JQTB-rOAIGbMM1rOj6bvxrJ0YsvOplj5jKhkwF6nQsCIKWhtHpdqH7fR39UCJe5bOXypzaf9HEBR7S7pCHpg
2021-02-01 21:31:09 DEBUG upload.App (App.java:29) - Start to upload file C:/users/xmorningred88/testfile2
2021-02-01 21:31:10 INFO  Service.ArchiveUploadService (ArchiveUploadService.java:57) - C:/users/morningred88/testfile2 has been successfully uploaded. Archive ID is :: Mv_NuY7QN5LeT2xi7qRCusXX4YMtxf7ftO_Z7YGRMLitvfj9xPBM2eBybQEMw4EoNuVzehSrEPDCjl1851vluAZB8IJoBj8oKr36s3TS8hEApJPbTmF2lOUltO97j2NFP6Yo89-dyw
```

