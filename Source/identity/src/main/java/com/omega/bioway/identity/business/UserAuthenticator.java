package com.omega.bioway.identity.business;

import com.omega.bioway.identity.crosscutting.entities.User;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import com.omega.bioway.identity.crosscutting.security.Token;
import com.omega.bioway.identity.dataaccess.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAuthenticator {

    private final String PREFIX="Bearer ";
    private final String SECRET="bioway";
    private final String ID="identity";
    private final String CLAIM="authorities";
    private final int validity=60000;
    private final String ROLL="ROLE_";

    private UserRepository repository;

    @Autowired
    public UserAuthenticator(UserRepository repository) {
        this.repository = repository;
    }

    public List<String> execute(String email, String password){
        List<GrantedAuthority> grantedAuthorities= null;
        String token=null;
        List<String> data=new ArrayList<>();
        Optional<User> user=repository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
        if(user.get().getPassword().equals(password)){
            data.add(user.get().getId());
            grantedAuthorities=AuthorityUtils.commaSeparatedStringToAuthorityList(ROLL+user.get().getType());
            token=Jwts.builder()
                    .setId(ID)
                    .setSubject(email)
                    .claim(CLAIM,grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+validity))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
            token=PREFIX +token;
            data.add(token);
            return data;
        }
        return null;
    }
}
