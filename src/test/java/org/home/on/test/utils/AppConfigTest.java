package org.home.on.test.utils;

import org.home.on.utils.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { AppConfig.class })
public class AppConfigTest {

}
