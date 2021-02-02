package com.muhutech.s3.glacier.upload.model;

/**
 *
 * @author Lily Ma
 */
public class ArchiveLoadingInfo {
    
    //provide name of file to upload, including the file path
    String archiveFilePath;
    
    //provide vault name 
    String vaultName;

    public String getArchiveFilePath() {
        return archiveFilePath;
    }

    public void setArchiveFilePath(String archiveFilePath) {
        this.archiveFilePath = archiveFilePath;
    }


    public String getVaultName() {
        return vaultName;
    }

    public void setVaultName(String vaultName) {
        this.vaultName = vaultName;
    }  
    
}

