package application.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CustomAuthenticationProviderTest {

    private CustomAuthenticationProvider authenticationProvider;

    @Before
    public void setUp() throws Exception {
        authenticationProvider = new CustomAuthenticationProvider();
    }

    @After
    public void tearDown() throws Exception {
        authenticationProvider = null;
    }

    @Test
    public void testAuthenticate() throws Exception {
        UsernamePasswordAuthenticationToken token1 =
                new UsernamePasswordAuthenticationToken("admin", "system");

        Authentication auth = authenticationProvider.authenticate(token1);
        String principal = ((User) auth.getPrincipal()).getUsername();
        String password = auth.getCredentials().toString();
        List<GrantedAuthority> GrantedAuthority = auth.getAuthorities().stream().collect(Collectors.toList());

        assertEquals(principal, "admin");
        assertEquals(password, "system");
        assertEquals(GrantedAuthority.size(), 1);
        assertEquals(GrantedAuthority.get(0).getAuthority(), "ROLE_ADMIN");

        UsernamePasswordAuthenticationToken token2 =
                new UsernamePasswordAuthenticationToken("user", "user");

        auth = authenticationProvider.authenticate(token2);
        principal = ((User) auth.getPrincipal()).getUsername();
        password = auth.getCredentials().toString();
        GrantedAuthority = auth.getAuthorities().stream().collect(Collectors.toList());

        assertEquals(principal, "user");
        assertEquals(password, "user");
        assertEquals(GrantedAuthority.size(), 1);
        assertEquals(GrantedAuthority.get(0).getAuthority(), "ROLE_USER");


        UsernamePasswordAuthenticationToken token3 =
                new UsernamePasswordAuthenticationToken("admin", "user");

        auth = authenticationProvider.authenticate(token3);
        assertEquals(auth, null);

        UsernamePasswordAuthenticationToken token4 =
                new UsernamePasswordAuthenticationToken("user", "admin");

        auth = authenticationProvider.authenticate(token4);
        assertEquals(auth, null);

    }

}