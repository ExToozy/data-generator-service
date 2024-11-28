package ru.extoozy.datageneratorservice.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class BeanConfig {

    @SneakyThrows
    @Bean
    public XML producerXML() {
        try (InputStream inputStream = getClass().getResourceAsStream("/kafka/producer.xml")) {
            return new XMLDocument(inputStream.readAllBytes());
        }
    }
}
