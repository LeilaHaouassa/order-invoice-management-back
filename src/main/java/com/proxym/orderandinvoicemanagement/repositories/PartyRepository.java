package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyIdentification;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PartyRepository extends MongoRepository<Party, String> {

    Optional<Party> findPartyByTechnicalId(String id);
    Boolean existsByPartyName(PartyName partyName);
    Boolean existsByPartyIdentification(PartyIdentification id);

}
