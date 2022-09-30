package com.proxym.orderandinvoicemanagement.configuration;


import com.proxym.orderandinvoicemanagement.dto.commun.*;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;
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
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(PartyDTO.class, Party.class).addMappings(mapper ->
                mapper.skip(Party::setTechnicalId)
        );
        modelMapper.typeMap(OrderDTO.class, Order.class).addMappings(mapper ->
                    mapper.skip(Order::setTechnicalId)
        );
        modelMapper.typeMap(OrderChangeDTO.class, OrderChange.class).addMappings(mapper ->
                mapper.skip(OrderChange::setTechnicalId)
        );
        modelMapper.typeMap(OrderCancellationDTO.class, OrderCancellation.class).addMappings(mapper ->
            mapper.skip(OrderCancellation::setTechnicalId)
        );
        modelMapper.typeMap(OrderResponseSimpleDTO.class, OrderResponseSimple.class).addMappings(mapper ->
                mapper.skip(OrderResponseSimple::setTechnicalId)
        );
        modelMapper.typeMap(OrderResponseDTO.class, OrderResponse.class).addMappings(mapper ->
                mapper.skip(OrderResponse::setTechnicalId)
        );
        modelMapper.typeMap(CustomerPartyDTO.class, CustomerParty.class).addMappings(mapper ->
                mapper.skip(CustomerParty::setParty)
        );
        modelMapper.typeMap(SupplierPartyDTO.class, SupplierParty.class).addMappings(mapper ->
                mapper.skip(SupplierParty::setParty)
        );

        return modelMapper;
    }
}


