package com.muhutech.aws.s3.glacier.upload;


import com.muhutech.aws.s3.glacier.upload.Service.ArchiveUploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lily Ma
 */
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    private final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    ArchiveUploadService archiveUploadService = (ArchiveUploadService) ctx.getBean("archiveUploadService");
    
    public static void main(String[] args) {
        App app = new App();
        for (String arg : args) {
           app.executeUpload(arg); 
        }
        }
    
    private void executeUpload(String archiveToUpload) {       
                 
        LOGGER.debug("Start to upload file " + archiveToUpload);
        
        archiveUploadService.uploadFilesToGlacier(archiveToUpload);
        
    }
}
