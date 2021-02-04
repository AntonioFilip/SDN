package com.antonio.SDNVodafone.Config;

import com.antonio.SDNVodafone.Entities.City;
import com.antonio.SDNVodafone.Entities.Network;
import com.antonio.SDNVodafone.Entities.NetworkInfo;
import com.antonio.SDNVodafone.Entities.Region;
import com.antonio.SDNVodafone.Repositories.CityRepository;
import com.antonio.SDNVodafone.Repositories.NetworkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class LoadDatabaseConfiguration {
    //private static final Logger logger = LoggerFactory.getLogger(LoadDatabaseConfiguration.class);

    @Bean
    public CommandLineRunner initiateDatabase(CityRepository cityRepository, NetworkRepository networkRepository){
        return args -> {

            NetworkInfo x1 = new NetworkInfo("255.255.255.0", true, "30/01/20 17:00:00");
            NetworkInfo x2 = new NetworkInfo("192.168.0.4", true, "30/01/20 17:00:00");
            NetworkInfo x3 = new NetworkInfo("192.168.0.2", false, "30/01/20 17:00:00");

            Network y1 = new Network("192.168.0.0/24");
            Network y2 = new Network("192.168.10.0/24");

            y1.addNetworkInfo(x1);
            y1.addNetworkInfo(x2);
            y2.addNetworkInfo(x3);
            y2.addNetworkInfo(x1);

            Region z1 = new Region("BER-1",1);
            z1.addNetwork(y1);
            z1.addNetwork(y2);
            City c1 = new City("Berlin");
            c1.addRegion(z1);

            cityRepository.save(c1);
        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
