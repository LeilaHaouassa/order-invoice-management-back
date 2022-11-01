package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.ItemDTO;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;
import com.proxym.orderandinvoicemanagement.repositories.ItemRepository;
import com.proxym.orderandinvoicemanagement.services.IItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<Item> getAll() {
        return itemRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Item saveItem(Item item) {
        if(item != null){
            return itemRepository.insert(item);
        }
        return null;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO,Item.class);
        item = saveItem(item);
        return modelMapper.map(item,ItemDTO.class);
    }


}
