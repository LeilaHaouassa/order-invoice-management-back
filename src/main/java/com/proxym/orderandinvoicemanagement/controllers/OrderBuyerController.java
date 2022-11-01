package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderCancellationDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.services.IOrderBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/customer-service/orders")
public class OrderBuyerController {

    @Autowired
    private IOrderBuyerService buyerService;

    @GetMapping("/sent")
    public ResponseEntity<Set<OrderDTO>> getAllSentOrders(@PathVariable("partyId") String buyerPartyId){
        return ResponseEntity.ok().body(buyerService.getAllSentOrders(buyerPartyId));
    }

    @PostMapping("/send")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable("partyId") String buyerPartyId, @RequestBody OrderDTO orderDTO){
        //TODO switch to created Http Response
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/{partyId}/customerSide/orders/send").build().toUriString());
        return ResponseEntity.ok().body(buyerService.placeOrder(buyerPartyId,orderDTO));
    }

    @PostMapping("/change")
    public ResponseEntity<OrderDTO> changeOrder(@PathVariable("partyId") String buyerPartyId,@RequestBody OrderChangeDTO orderChangeDTO){
        return ResponseEntity.ok().body(buyerService.changeOrder(buyerPartyId, orderChangeDTO));
    }

    @PostMapping("/cancel")
    public ResponseEntity<Set<OrderDTO>> cancelOrder(@PathVariable("partyId") String buyerPartyId,@RequestBody OrderCancellationDTO orderCancellationDTO){
        return ResponseEntity.ok().body(buyerService.cancelOrder(buyerPartyId, orderCancellationDTO));
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptOrder(@RequestBody String orderTechnicalIdReceived){
        String orderTechnicalId = orderTechnicalIdReceived.substring(1,orderTechnicalIdReceived.length()-1);
        return ResponseEntity.ok().body(buyerService.acceptOrder(orderTechnicalId));
    }

    @GetMapping("/{orderId}/prep-change-document")
    public  ResponseEntity<OrderResponseDTO> getDocumentForOrderChange(@PathVariable("orderId") String orderTechnicalId){
        return ResponseEntity.ok().body(buyerService.getDocumentForOrderChange(orderTechnicalId));
    }

}