package br.com.coopera.gestorvotacao.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMappingConfig {

    @Bean
    public ModelMapper getModelMapping(){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;

    }

}
