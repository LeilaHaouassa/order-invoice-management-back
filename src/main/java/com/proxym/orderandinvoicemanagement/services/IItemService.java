package com.proxym.orderandinvoicemanagement.services;


import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;

import java.util.Set;

public interface IItemService {

    Set<Item> getAll();

}
