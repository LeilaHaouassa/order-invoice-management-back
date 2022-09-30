package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;
import com.proxym.orderandinvoicemanagement.repositories.ItemRepository;
import com.proxym.orderandinvoicemanagement.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Set<Item> getAll() {
        return itemRepository.findAll().stream().collect(Collectors.toSet());
    }
}
