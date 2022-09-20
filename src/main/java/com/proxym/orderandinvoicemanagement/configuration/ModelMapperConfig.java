package com.proxym.orderandinvoicemanagement.configuration;


import com.proxym.orderandinvoicemanagement.dto.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.*;
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
            mapper.skip(Party::setTechnicalId);

        });
        modelMapper.typeMap(PartyNameDTO.class, PartyName.class).addMappings(mapper -> {
           mapper.skip(PartyName::setTechnicalId);
        });
        modelMapper.typeMap(AddressDTO.class, Address.class).addMappings(mapper -> {
            mapper.skip(Address::setTechnicalId);
        });
        modelMapper.typeMap(ContactDTO.class, Contact.class).addMappings(mapper -> {
            mapper.skip(Contact::setTechnicalId);
        });
        modelMapper.typeMap(FinancialAccountDTO.class, FinancialAccount.class).addMappings(mapper -> {
            mapper.skip(FinancialAccount::setTechnicalId);
        });
        modelMapper.typeMap(PartyIdentificationDTO.class, PartyIdentification.class).addMappings(mapper -> {
            mapper.skip(PartyIdentification::setTechnicalId);
        });

        return modelMapper;
    }
}


