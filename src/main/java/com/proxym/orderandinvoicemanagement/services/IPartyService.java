package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyRefDTO;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;

import java.util.List;
import java.util.Set;

 public interface IPartyService {

     Set<PartyDTO> getAll();

     Set<PartyRefDTO> getAllOtherParties(String callerPartyId);

     Party saveParty(Party party);

     PartyDTO getPartyById(String id) throws ResourceNotFoundException;

     PartyDTO createParty(PartyDTO party) throws IllegalArgumentException;

     PartyDTO updateParty(String id, PartyDTO partyDTO) throws ResourceNotFoundException,IllegalArgumentException;

     Set<PartyDTO> convertListToDTO(List<Party> list);

     void deleteParty(String id) throws ResourceNotFoundException;
}

