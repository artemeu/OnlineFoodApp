package com.bilgeadam.onlinefoodapp.jwt.resource;

import com.bilgeadam.onlinefoodapp.jwt.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer")
public class JwtAuthenticationCustomerController {

    private final AuthenticationManager authenticationManagerCustomer;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsCustomerService jwtUserDetailsCustomerService;

    public JwtAuthenticationCustomerController(@Qualifier("authenticationManagerCustomer") AuthenticationManager authenticationManagerCustomer,
                                               JwtTokenUtil jwtTokenUtil,
                                               JwtUserDetailsAdminService jwtUserDetailsAdminService,
                                               JwtUserDetailsCustomerService jwtUserDetailsCustomerService) {
        this.authenticationManagerCustomer = authenticationManagerCustomer;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsCustomerService = jwtUserDetailsCustomerService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsCustomerService.loadUserByUsername(authenticationRequest.getUsername());
        List roles = (List) userDetails.getAuthorities();
        final String token = jwtTokenUtil.generateToken(userDetails,"customer");

        return ResponseEntity.ok(new JwtTokenResponse(token, roles));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtCustomerDetails user = (JwtCustomerDetails) jwtUserDetailsCustomerService.loadUserByUsername(username);
        List roles = (List) user.getAuthorities();
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
        String refreshedToken = jwtTokenUtil.refreshToken(token);
        return ResponseEntity.ok(new JwtTokenResponse(refreshedToken, roles));
    } else {
        return ResponseEntity.badRequest().body(null);
    }
}

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManagerCustomer.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}