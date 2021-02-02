package com.muhutech.aws.s3.glacier.upload.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.UploadResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lily Ma
 */
@Service
public class ArchiveUploadService {

    private static final Logger LOGGER = LogManager.getLogger(ArchiveUploadService.class.getName());

    public static AmazonGlacierClient client;

    private String vaultName;

    /**
     * Uploading an Archive Using the High-Level API
     *
     * @param archiveToUpload
     * @return
     */
    public String uploadFilesToGlacier(String archiveToUpload) {

        String responseMessage;

        ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();

        client = new AmazonGlacierClient(credentials);

        client.setEndpoint("https://glacier.us-east-2.amazonaws.com/");
        try {
            ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);

            UploadResult result = atm.upload(vaultName, "my archive " + (new Date()), new File(archiveToUpload));

            String archiveId = result.getArchiveId();

            responseMessage = archiveToUpload + " has been successfully uploaded. Archive ID is :: " + archiveId;

        } catch (AmazonClientException | FileNotFoundException e) {

            responseMessage = archiveToUpload + " uploading failed :: " + e;
        }

        LOGGER.info(responseMessage);
        return responseMessage;
    }

    public void setVaultName(String vaultName) {
        this.vaultName = vaultName;
    }

}
