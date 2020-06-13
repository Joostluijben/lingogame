package joost.luijben.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public AppConfig() {
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
