package com.datvutech.licenseservice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> claims = jwt.getClaims();
        Assert.notNull(claims, "Claims must be not null");
        
        Object realmAccessObj = claims.get("realm_access");
        String realmAccess = realmAccessObj == null ? "" : realmAccessObj.toString();
        try {
            ObjectMapper objMapper = new ObjectMapper();
            JsonNode realmAccessNode = objMapper.readTree(realmAccess);
            JsonNode roles = realmAccessNode.get("roles");
            if (roles != null && roles.isArray()) {
                for (JsonNode role : roles) {
                    String roleValue = "ROLE_" + role.asText();
                    authorities.add(new SimpleGrantedAuthority(roleValue));
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Cannot read roles value in token", e);
            return authorities;
        }

        return authorities;
    }

}
