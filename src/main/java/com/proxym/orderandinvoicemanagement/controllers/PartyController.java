package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.dto.PartyDTO;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.services.Implementations.PartyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties")
public class PartyController {
    @Autowired
    private PartyServiceImpl partyService;

    @GetMapping
    public ResponseEntity<Set<PartyDTO>> getAllParties(){
        return ResponseEntity.ok().body(partyService.getAll());
    }

    @GetMapping("/{partyName}/details")
    public ResponseEntity<PartyDTO> getPartyByName(@PathVariable String partyName) throws Exception {
        return  ResponseEntity.ok().body(partyService.getPartyByName(partyName));
    }

    @PostMapping("/add")
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/add").toUriString());
        return ResponseEntity.created(uri).body(partyService.createParty(partyDTO));
    }

    @PostMapping("/{partyName}/update")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable String partyName,@RequestBody PartyDTO partyDTO) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/{partyName}/update").toUriString());
        return ResponseEntity.created(uri).body(partyService.updateParty(partyName,partyDTO));
    }



}
