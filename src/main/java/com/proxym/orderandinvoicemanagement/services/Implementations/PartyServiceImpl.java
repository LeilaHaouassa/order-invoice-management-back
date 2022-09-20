package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.PartyDTO;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyIdentification;
import com.proxym.orderandinvoicemanagement.model.communEntities.PartyName;
import com.proxym.orderandinvoicemanagement.repositories.PartyRepository;
import com.proxym.orderandinvoicemanagement.services.IPartyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartyServiceImpl implements IPartyService {
    @Autowired
    public PartyRepository partyRepository;
    @Autowired
    public ModelMapper modelMapper;


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

    //Utility function: needed by checkIfPartyNameTaken
    @Override
    public Boolean existsByPartyName(TextType partyName) {
        PartyName name = new PartyName(partyName);
        return partyRepository.existsByPartyName(name);
    }

    //Utility function: needed by checkIfPartyIdentificationTaken
    @Override
    public Boolean existsByPartyIdentification(IdentifierType id) {
        PartyIdentification identification = new PartyIdentification(id);
        return partyRepository.existsByPartyIdentification(identification);
    }

    @Override
    public Set<PartyDTO> getAll() {
        List<Party> parties = partyRepository.findAll();
        return convertListToDTO(parties);
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
        if (checkIfPartyIdentificationTaken(partyDTO)) {
            throw new IllegalArgumentException("Unvalid Input: Id of party already taken");
        }
        if (checkIfPartyNameTaken(partyDTO)) {
            throw new IllegalArgumentException("Unvalid Input: Name of party already taken");
        }
        Party party = modelMapper.map(partyDTO, Party.class);
        party = saveParty(party);
        return modelMapper.map(party, PartyDTO.class);
    }


    //Utility function: needed upon creation and update of party
    @Override
    public Boolean checkIfPartyNameTaken(PartyDTO partyDTO) {
        PartyName partyName = new PartyName(partyDTO.getPartyName().getName());
        return partyRepository.existsByPartyName(partyName);
    }

    //Utility function: needed upon creation and update of party
    @Override
    public Boolean checkIfPartyIdentificationTaken(PartyDTO partyDTO) {
        PartyIdentification identification = new PartyIdentification(partyDTO.getPartyIdentification().getId());
        return partyRepository.existsByPartyIdentification(identification);
    }

    @Override
    public PartyDTO updateParty(String id, PartyDTO partyDTO) throws ResourceNotFoundException, IllegalArgumentException {
        Party partyToUpdate = partyRepository.findPartyByTechnicalId(id).orElse(null);
        //check if the provided id is valid or not
        if (partyToUpdate == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Party with Id " + id + " not found.");
        }
        //verify if the new PartyIdentifier isn't taken in case it changes
        if (!partyDTO.getPartyIdentification().getId().getIdentifierContent().equals(partyToUpdate.getPartyIdentification().getId().getIdentifierContent())) {
            if (checkIfPartyIdentificationTaken(partyDTO)) {
                throw new IllegalArgumentException("Unvalid Input: Id of party already taken");
            }
        }

        //in case the company goes through rebranding and change the company name
        //verify if the new name isn't taken
        if (!partyDTO.getPartyName().getName().getTextContent().equals(partyToUpdate.getPartyName().getName().getTextContent())) {
            if (checkIfPartyNameTaken(partyDTO)) {
                throw new IllegalArgumentException("Unvalid Input: Name of party already taken");
            }
        }
        Party partyWithChangeInfo = modelMapper.map(partyDTO, Party.class);
        partyWithChangeInfo.setTechnicalId(partyToUpdate.getTechnicalId());
        Party updatedParty = partyRepository.save(partyWithChangeInfo);
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

