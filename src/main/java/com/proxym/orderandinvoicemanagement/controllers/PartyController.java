package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.services.IPartyService;
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
    private IPartyService partyService;

    @GetMapping
    public ResponseEntity<Set<PartyDTO>> getAllParties(){
        return ResponseEntity.ok().body(partyService.getAll());
    }

    @GetMapping("/{partyId}")
    public ResponseEntity<PartyDTO> getPartyByTechnicalId(@PathVariable String partyId) {
        return  ResponseEntity.ok().body(partyService.getPartyById(partyId));
    }

    @PostMapping("/add")
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO)  {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/add").toUriString());
        return ResponseEntity.created(uri).body(partyService.createParty(partyDTO));
    }

    @PostMapping("/update/{partyId}")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable String partyId,@RequestBody PartyDTO partyDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/{partyId}/update").toUriString());
        return ResponseEntity.created(uri).body(partyService.updateParty(partyId,partyDTO));
    }

    @GetMapping("/delete/{partyId}")
    public ResponseEntity<?> deleteParty(@PathVariable String partyId){
        partyService.deleteParty(partyId);
        return ResponseEntity.ok("Party with Id " + partyId + "is removed successfully");
    }


}
