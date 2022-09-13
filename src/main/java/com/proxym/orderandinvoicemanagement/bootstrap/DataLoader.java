package com.proxym.orderandinvoicemanagement.bootstrap;

import com.proxym.orderandinvoicemanagement.model.baseEntities.CodeType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.*;
import com.proxym.orderandinvoicemanagement.security.service.RoleService;
import com.proxym.orderandinvoicemanagement.services.Implementations.PartyServiceImpl;
import com.proxym.orderandinvoicemanagement.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PartyServiceImpl partyService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {


//        Party party1 =  Party.builder().
//                partyName(PartyName.builder()
//                        .name(
//                            TextType.builder().textContent("ZARA").build())
//                        .build())
//                .contact(Contact.builder()
//                        .name(
//                            TextType.builder().textContent("moula zara").build())
//                        .telephone(
//                                TextType.builder().textContent("58963741").build())
//                        .electronicMail(
//                                TextType.builder().textContent("moula@zara.tn").build()
//                        )
//                        .build())
//                .financialAccount( FinancialAccount.builder()
//                        .id(
//                                IdentifierType.builder().identifierContent("4555555556888").build()
//                        )
//                        .currencyCode(
//                                CodeType.builder().codeContent("TND").build())
//                        .build())
//                .postalAddress( Address.builder()
//                        .cityName(
//                                TextType.builder().textContent("Sousse").build())
//                        .postalZone(TextType.builder().textContent("4002").build())
//                        .country( Country.builder().name(
//                                TextType.builder().textContent("Tunisie").build()
//                        ).build())
//                        .build())
//                .build();
//
//        System.out.println(party1);
//        partyService.save(party1);

//        userService.saveUser( new CustomUser("John Travolta","john@jhannem.tn","pass123"));
//        userService.saveUser(  new CustomUser("John weld3amou","cousin@jhannem.tn","pass123"));
//        userService.saveUser( new CustomUser("John bouh","father@jhannem.tn","pass123"));
//        ERole eRole = ERole.valueOf("USER");
//        Role role1 = new Role(eRole);
//        roleService.saveRole(role1);
//
//        userService.addRoleToUser("user","john@jhannem.tn");
//        userService.addRoleToUser("user","cousin@jhannem.tn");
//        userService.addRoleToUser("user","father@jhannem.tn");

    }
}
