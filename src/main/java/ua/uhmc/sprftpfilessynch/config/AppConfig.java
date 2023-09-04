package ua.uhmc.sprftpfilessynch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.uhmc.sprftpfilessynch.handler.BinariesHandler;
import ua.uhmc.sprftpfilessynch.provider.ApplicationContextProvider;

@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Bean
    @Scope("prototype")
    public BinariesHandler binariesHandlerPrototypeScope() {
        return new BinariesHandler();
    }

//    @Bean
//    @Scope("prototype")
//    public JsonParser jsonParserPrototypeScope() {
//        return new JsonParser();
//    }

}
