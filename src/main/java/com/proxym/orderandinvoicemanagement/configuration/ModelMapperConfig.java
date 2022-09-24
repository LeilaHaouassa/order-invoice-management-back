package com.proxym.orderandinvoicemanagement.configuration;


import com.proxym.orderandinvoicemanagement.dto.commun.*;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.*;
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
                .setMatchingStrategy(MatchingStrategies.STANDARD);
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

        return modelMapper;
    }
}


