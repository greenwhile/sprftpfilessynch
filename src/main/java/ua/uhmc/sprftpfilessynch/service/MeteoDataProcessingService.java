package ua.uhmc.sprftpfilessynch.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ua.uhmc.sprftpfilessynch.config.Constants;
import ua.uhmc.sprftpfilessynch.handler.BinariesHandler;
import ua.uhmc.sprftpfilessynch.provider.ApplicationContextProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MeteoDataProcessingService {

    private ApplicationContext context = ApplicationContextProvider.getApplicationContext();
    //    private JsonParser jsonParser = (JsonParser) context.getBean("jsonParserPrototypeScope");
    private BinariesHandler binariesHandler = (BinariesHandler) context.getBean("binariesHandlerPrototypeScope");

    @RabbitListener(queues = Constants.BINARY_QUEUE)
    public void consumeMessageFromQueue(String message) throws IOException {
        System.out.println("From MeteoDataProcessingService.class receiver: " + message);
        List<File> binaries = binariesHandler.getBinaryDataFilesList();
        System.out.println("binaries sz: " + binaries.size());
        for(File file : binaries){
            System.out.println("file bin: " + file.getName());
            byte [] bytes = binariesHandler.cutIntoGrib2Files(Files.readAllBytes(Paths.get(file.getPath())), Path.of(file.getPath()));
        }
//            logger.info("Message Received from queue: " + message );
    }
    public void checkHandlers() throws IOException {

        System.out.println("Hello there : )) ");

//        List<File> binaries = binariesHandler.getBinaryDataFilesList();
//        System.out.println("binaries sz: " + binaries.size());
//        for(File file : binaries){
//            System.out.println("file bin: " + file.getPath());
//            byte [] bytes = binariesHandler.cutIntoGrib2Files(Files.readAllBytes(Paths.get(file.getPath())));
//        }


//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("remotedirectories.txt").getFile());
//        System.out.println("canonical: " + file.getCanonicalPath() + " path: " + file.getPath() + " name: " + file.getName());

        /*
        List<String> directories = jsonParser.getRemoteDirectoriesList();
        System.out.println("pth ====>>>>   " + directories);

        for(String remoteFtpDirectory : directories){
            List<File> files = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles(remoteFtpDirectory);
            System.out.println("files ====>>>>   " + files);
        }
        */
    }
}
