package com.proxym.orderandinvoicemanagement.model.orderEntities;

public enum OrderStatus {
        CONFIRMED, //means seller accepted order
        REJECTED, //means seller rejected order
        PENDING,  //means waiting for response from seller
        CHANGED, // means buyer made changes to the order
        NEGOTIATING,  //means either seller gave counter-offer
        CANCELLED,  //means buyer canceled order
        ACCEPTED  //either buyer or seller accepted order (depending on settings)
}
