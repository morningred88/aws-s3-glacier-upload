package com.muhutech.s3.glacier.upload.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.UploadResult;
import com.muhutech.s3.glacier.upload.model.ArchiveLoadingInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lily Ma
 */
@RestController
public class GlacierController {
    
    public static AmazonGlacierClient client;

    @RequestMapping(value = "/archive", method = RequestMethod.POST)
    public String uploadFilesToGlacier(@RequestBody ArchiveLoadingInfo archiveLoadingInfo) {

        String responseMessage;
        
        String archiveToUpload = archiveLoadingInfo.getArchiveFilePath();

        String vaultName = archiveLoadingInfo.getVaultName();

        ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
        
        client = new AmazonGlacierClient(credentials);

        client.setEndpoint("https://glacier.us-east-2.amazonaws.com/");
        try {
            ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);

            UploadResult result = atm.upload(vaultName, "my archive " + (new Date()), new File(archiveToUpload));

            String archiveId = result.getArchiveId();
            
            responseMessage = "Archive ID: " + archiveId;

        } catch (AmazonClientException | FileNotFoundException e) {
            
            responseMessage = "Error || " + e;
        }
        return responseMessage;
    }

}
