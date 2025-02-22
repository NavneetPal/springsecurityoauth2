package com.nav.springsecurityoauth2.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name="user_name",nullable=false)
    private String name;
    private String email;

    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length=1000)
    private String about;

    @Column(length=1000)
    private String profilePic;
    private String phoneNumber;
    private String emailToken;

    @Getter(AccessLevel.NONE)
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    //SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    //For this project emailid is our username
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }
}
