package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyRefDTO;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;
import com.proxym.orderandinvoicemanagement.repositories.PartyRepository;
import com.proxym.orderandinvoicemanagement.services.IPartyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartyServiceImpl implements IPartyService {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Set<PartyDTO> convertListToDTO(List<Party> list) {
        return list.stream().map(party -> modelMapper.map(party, PartyDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public PartyDTO getPartyById(String id) throws ResourceNotFoundException {
        Party party = partyRepository.findPartyByTechnicalId(id).orElse(null);
        if (party == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Party with id " + id + " not found");
        }
        return modelMapper.map(party, PartyDTO.class);
    }


    @Override
    public Set<PartyDTO> getAll() {
        List<Party> parties = partyRepository.findAll();
        return convertListToDTO(parties);
    }

    @Override
    public Set<PartyRefDTO> getAllOtherParties(String callerPartyId) {
        List<Party> parties = partyRepository.findAll();
        Party excludedParty =  partyRepository.findPartyByTechnicalId(callerPartyId).orElse(null);
        if (excludedParty == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Party with id " + callerPartyId + " not found");
        }
        parties.remove(excludedParty);
        return parties.stream().map(party -> modelMapper.map(party, PartyRefDTO.class)).collect(Collectors.toSet());

    }

    @Override
    public Party saveParty(Party party) {
        if (party != null) {
            return partyRepository.insert(party);
        }
        return null;
    }


    @Override
    public PartyDTO createParty(PartyDTO partyDTO) throws IllegalArgumentException {
        Party party = modelMapper.map(partyDTO, Party.class);
        try {
            party = saveParty(party);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid Input: Id or name of party is already taken. Please provide new values for them.", exception);
        }
        return modelMapper.map(party, PartyDTO.class);
    }


    @Override
    public PartyDTO updateParty(String id, PartyDTO partyDTO) throws ResourceNotFoundException, IllegalArgumentException {
        Party partyToUpdate = partyRepository.findPartyByTechnicalId(id).orElse(null);
        if (partyToUpdate == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Party with Id " + id + " not found.");
        }
        Party partyWithChangeInfo = modelMapper.map(partyDTO, Party.class);
        partyWithChangeInfo.setTechnicalId(partyToUpdate.getTechnicalId());
        Party updatedParty;
        try {
            updatedParty = partyRepository.save(partyWithChangeInfo);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid Input: Id or name of party is already taken. Please provide new values for them.", exception);
        }
        return modelMapper.map(updatedParty, PartyDTO.class);
    }

    @Override
    public void deleteParty(String id) throws ResourceNotFoundException {
        Party party = partyRepository.findPartyByTechnicalId(id).orElse(null);
        if (party == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Party with Id " + id + " not found.");
        }
        partyRepository.delete(party);
    }


}

