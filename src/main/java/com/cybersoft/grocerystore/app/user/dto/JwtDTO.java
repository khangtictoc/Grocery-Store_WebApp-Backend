package com.cybersoft.grocerystore.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDTO {
    String type;
    String value;
}
