package ua.uhmc.sprftpfilessynch.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ua.uhmc.sprftpfilessynch.config.Constants;
import ua.uhmc.sprftpfilessynch.handler.BinariesHandler;
import ua.uhmc.sprftpfilessynch.provider.ApplicationContextProvider;

import java.io.IOException;

@Service
public class MeteoDataProcessingService {

    private ApplicationContext context = ApplicationContextProvider.getApplicationContext();
    //    private JsonParser jsonParser = (JsonParser) context.getBean("jsonParserPrototypeScope");
    private BinariesHandler binariesHandler = (BinariesHandler) context.getBean("binariesHandlerPrototypeScope");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = Constants.BINARY_QUEUE)
    public void consumeMessageFromQueue(String message) throws IOException {
        System.out.println("From MeteoDataProcessingService.class receiver: " + message);
//        List<File> binaries = binariesHandler.getBinaryDataFilesList();
//        System.out.println("binaries sz: " + binaries.size());
//        for(File file : binaries){
//            if(file.getName().equals(message)){
//                System.out.println("file bin: " + file.getName());
//                Path temp = Files.createTempDirectory("temp");
//                System.out.println("tmp == " + temp);
//                String messagetosend = temp.toString().concat(":").concat(file.getName());
//                byte [] bytes = binariesHandler.cutIntoGrib2Files(Files.readAllBytes(Paths.get(file.getPath())), temp, Path.of(file.getPath()));
//                rabbitTemplate.convertAndSend(Constants.METEO_DATA_EXCHANGE,Constants.GRIB2_ROUTING_KEY, messagetosend);
//            } else {
//                throw new IOException("FROM MeteoDataProcessingService.class:consumeMessageFromQueue() ---> There is no such file in dir");
//            }
//
//        }
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
