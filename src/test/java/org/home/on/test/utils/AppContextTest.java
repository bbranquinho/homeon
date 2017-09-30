package org.home.on.test.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.home.on.utils.AppConfig;

@Configuration
@Import(value = { AppConfig.class })
@ComponentScan(basePackages = { "org.home.on.test" })
public abstract class AppContextTest {

}
