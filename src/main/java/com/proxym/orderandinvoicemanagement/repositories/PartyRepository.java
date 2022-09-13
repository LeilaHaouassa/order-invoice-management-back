package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyIdentification;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PartyRepository extends MongoRepository<Party, String> {

    //Be careful of case sensitivity
    Optional<Party> findPartyByPartyName(PartyName partyName);
    Boolean existsByPartyName(PartyName partyName);
    Boolean existsByPartyIdentification(PartyIdentification id);

}
