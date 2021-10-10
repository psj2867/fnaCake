package ml.psj2867.demo.configure.security;

import org.springframework.security.core.GrantedAuthority;

public enum AuthEnum implements GrantedAuthority{
     CREATOR
    ,ANONYMOUS
    ,USER;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
    
}
