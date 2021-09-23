package com.omega.bioway.identity.business;

import com.omega.bioway.identity.crosscutting.entities.User;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserAlreadyExistException;
import com.omega.bioway.identity.dataaccess.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCreator {

    private final String PREFIX="Bearer ";
    private final String SECRET="bioway";
    private final String ID="identity";
    private final String CLAIM="authorities";
    private final int validity=60000;
    private final String ROLL="ROLL_";

    private UserRepository repository;

    @Autowired
    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    public List<String> execute(String id,String email, String password, String type){
        Optional<User> user=repository.findByEmail(email);
        List<String> data=new ArrayList<>();
        if(user.isPresent()){
            throw new UserAlreadyExistException("User with email: "+email+" already exists");
        }
        user=repository.findById(id);
        if(user.isPresent()){
            throw new UserAlreadyExistException("User with id: "+id+" already exists");
        }
        User nUser=new User(id,email,password,type);
        repository.save(nUser);
        List<GrantedAuthority> grantedAuthorities= null;
        String token=null;
        data.add(nUser.getId());
        grantedAuthorities= AuthorityUtils.commaSeparatedStringToAuthorityList(ROLL+nUser.getType());
        token= Jwts.builder()
                .setId(ID)
                .setSubject(nUser.getEmail())
                .claim(CLAIM,grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+validity))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
        data.add(token);
        return data;
    }
}
