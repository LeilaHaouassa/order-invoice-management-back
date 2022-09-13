package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.PartyDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyIdentification;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyName;

import java.util.List;
import java.util.Set;

 public interface IPartyService {

     Set<PartyDTO> getAll();

     Party saveParty(Party party);

     PartyDTO getPartyByName(String partyName) throws Exception;

     PartyDTO createParty(PartyDTO party) throws Exception;

     PartyDTO updateParty(String currentPartyName, PartyDTO partyDTO) throws Exception;

     Set<PartyDTO> convertListToDTO(List<Party> list);

     Boolean existsByPartyName(TextType name);

     Boolean existsByPartyIdentification(IdentifierType id);

     PartyName generatePartyNameObjFromString(String partyName);

     Party getPartyByNameLogic(String partyName) throws Exception;

     Boolean checkIfPartyNameTaken(PartyDTO partyDTO);

     Boolean checkIfPartyIdentificationTaken(PartyDTO partyDTO);

     PartyIdentification generatePartyIdentificationObjFromString(String id);
}

