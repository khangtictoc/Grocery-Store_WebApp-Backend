package com.cybersoft.grocerystore.app.user.config.gson;

import com.cybersoft.grocerystore.app.user.dto.UserIdentityDTO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class UserIdentityDTODeserializer implements JsonDeserializer<UserIdentityDTO> {

    @Override
    public UserIdentityDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Deserialize userIndentity field
        String userIndentity = jsonObject.get("userIndentity").getAsString();

        // Deserialize roles field using a custom deserializer for GrantedAuthority
        Type collectionType = new TypeToken<Collection<GrantedAuthority>>() {}.getType();
        Collection<GrantedAuthority> roles = context.deserialize(jsonObject.get("roles"), collectionType);

        return new UserIdentityDTO(roles, userIndentity);
    }
}

