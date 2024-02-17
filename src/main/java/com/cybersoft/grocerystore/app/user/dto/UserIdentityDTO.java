package com.cybersoft.grocerystore.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserIdentityDTO {
    Collection<GrantedAuthority> roles;
    String userIndentity;
}
