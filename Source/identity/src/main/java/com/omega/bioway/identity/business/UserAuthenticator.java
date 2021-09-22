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

    private UserRepository repository;

    @Autowired
    public UserAuthenticator(UserRepository repository) {
        this.repository = repository;
    }

    public Token execute(String email, String password){
        List<GrantedAuthority> grantedAuthorities= null;
        String token=null;
        Optional<User> user=repository.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                grantedAuthorities=AuthorityUtils.commaSeparatedStringToAuthorityList(user.get().getType());
                token=Jwts.builder()
                        .setId(ID)
                        .setSubject(email)
                        .claim(CLAIM,grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis()+validity))
                        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
                token=PREFIX +token;
                return new Token(token);
            }
            return null;
        }else{
            throw new UserNotFoundException("User with email: "+email+" not found");
        }
    }
}
