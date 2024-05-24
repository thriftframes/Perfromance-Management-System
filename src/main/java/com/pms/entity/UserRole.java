package com.pms.entity;


import com.pms.config.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static com.pms.config.Permission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {

        ADMIN,
        USER,
        MANAGER
        // Add other roles as needed
    }



//    private final Set<Permission> permissions;
//
//
//    public List<SimpleGrantedAuthority> getAuthorities() {
//
//        List<SimpleGrantedAuthority> authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
//        return authorities;
//    }



