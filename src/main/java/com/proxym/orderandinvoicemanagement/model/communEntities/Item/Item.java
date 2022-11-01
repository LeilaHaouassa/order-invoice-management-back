package com.proxym.orderandinvoicemanagement.model.communEntities.Item;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//"A class to describe an item of trade. It includes a generic
// description applicable to all examples of the item together
// with optional subsidiary descriptions of any number of
// actual instances of the type."
@Document
@Data
public class Item {
    @Id
    private String technicalId;
    //"A short name optionally given to this item, such as a
    // name from a catalogue, as distinct from a description."
    private TextType name;
}
