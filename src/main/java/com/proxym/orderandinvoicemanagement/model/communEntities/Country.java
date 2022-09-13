package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//"A class to describe a country."
@Data
@NoArgsConstructor  //for model mapping
public class Country {

    private TextType name;
}
