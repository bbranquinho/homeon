package org.home.on.utils;

import org.home.on.AppMain;
import org.home.on.agenda.AgendaRepository;
import org.home.on.agenda.Agendador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = AppMain.class)
public class AppConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.description}")
    private String appDescription;

    @Value("${spring.application.version}")
    private String appVersion;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private Agendador agendador;

    @PostConstruct
    public void onStartup() {
        agendaRepository
                .buscarAgendasAbertas(new Date())
                .stream()
                .forEach(a -> {
                    agendador.agendamento(a);
                });
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex(ResourcePaths.ROOT_PATH + "/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .build();
    }

    @Bean(name = "applicationProperty")
    public ApplicationProperty getApplicationProperty() {
        return new ApplicationProperty();
    }

    @Bean(name = "passwordEncoder")
    public StandardPasswordEncoder getStandardPasswordEncoder() {
        return new StandardPasswordEncoder(getApplicationProperty().getSecret());
    }

}
