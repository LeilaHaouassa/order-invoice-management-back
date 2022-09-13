package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.PartyDTO;
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
        return list.stream().map( party -> modelMapper.map(party,PartyDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public Boolean existsByPartyName(TextType partyName) {
        PartyName name = new PartyName(partyName);
        return partyRepository.existsByPartyName(name);
    }

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
    public PartyDTO getPartyByName(String partyName) throws Exception {
        Party party =  getPartyByNameLogic(partyName);
        return modelMapper.map(party,PartyDTO.class);
    }

    //find a better name
    @Override
    public Party getPartyByNameLogic(String partyName) throws Exception {
        PartyName partyNameObj = generatePartyNameObjFromString(partyName);
        Optional<Party> party= partyRepository.findPartyByPartyName(partyNameObj);
        if(party.isPresent()){
            return party.get();
        }else {
            throw new Exception("ERROR: INVALID NAME OF PARTY OR PARTY NOT FOUND");
        }
    }
    @Override
    public PartyName generatePartyNameObjFromString(String partyName) {
        return PartyName.builder().name(
                TextType.builder().textContent(partyName).build()
        ).build();
    }

    @Override
    public PartyDTO createParty(PartyDTO partyDTO) throws Exception {
        if(checkIfPartyIdentificationTaken(partyDTO)){
            throw new Exception("Id of party already taken");
        }
        if(checkIfPartyNameTaken(partyDTO)){
            throw new Exception("Name of party already taken");
        }
        Party party = modelMapper.map(partyDTO,Party.class);
        party= saveParty(party);
        return modelMapper.map(party,PartyDTO.class);
    }


    @Override
    public Boolean checkIfPartyNameTaken(PartyDTO partyDTO)  {
        PartyName partyName = new PartyName(partyDTO.getPartyName().getName());
        return partyRepository.existsByPartyName(partyName);
    }

    @Override
    public Boolean checkIfPartyIdentificationTaken(PartyDTO partyDTO) {
        PartyIdentification identification = new PartyIdentification(partyDTO.getPartyIdentification().getId());
        return partyRepository.existsByPartyIdentification(identification);
    }

    @Override
    public PartyIdentification generatePartyIdentificationObjFromString(String id) {
        return PartyIdentification.builder().id(
                IdentifierType.builder().identifierContent(id).build()
        ).build();
    }

    @Override
    public PartyDTO updateParty(String currentPartyName, PartyDTO partyDTO) throws Exception {
        //in case the company goes through rebranding and change the company name
        // we need to verify if the new name isn't taken
        if(!partyDTO.getPartyName().getName().getTextContent().equals(currentPartyName)){
            if(checkIfPartyNameTaken(partyDTO)){
                throw new Exception("Name of party already taken");
            }
        }
        //In case the party owner wants to update infos but not name
        Party partyToUpdate = getPartyByNameLogic(currentPartyName);
        Party partyWithChangeInfo= modelMapper.map(partyDTO,Party.class);
        partyWithChangeInfo.setPartyIdentification(partyToUpdate.getPartyIdentification());
        Party updatedParty = partyRepository.save(partyWithChangeInfo);
        return modelMapper.map(updatedParty,PartyDTO.class);
    }




}

