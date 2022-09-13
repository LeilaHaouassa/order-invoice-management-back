package com.proxym.orderandinvoicemanagement.configuration;


import com.proxym.orderandinvoicemanagement.dto.PartyDTO;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.typeMap(PartyDTO.class, Party.class).addMappings(mapper -> {
            mapper.skip(Party::setPartyIdentification);
        });
        return modelMapper;
    }
}


