package ua.uhmc.sprftpfilessynch.handler;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//import ua.uhmc.integration.config.FtpOutboundGatewayConfig;
//import ua.uhmc.integration.provider.ApplicationContextProvider;
//
//import java.io.File;
//import java.util.List;
//
//@Service
//public class FtpFilesHandler {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void boundFtpBinaryFiles(){
//        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//        List<File> files06 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+06/*");
//        List<File> files09 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+09/*");
//        List<File> files12 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+12/*");
//        List<File> files15 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+15/*");
//        List<File> files18 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+18/*");
//        List<File> files24 = context.getBean(FtpOutboundGatewayConfig.Gateway.class).fetchFiles("/GRIB2/COMPRESSED/EGRR/T+24/*");
//        System.out.println(files09);
//        System.out.println(files15);
//    }
//
//}
