package se.typelias.dndbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import se.typelias.dndbackend.models.AuthenticationRequest;
import se.typelias.dndbackend.models.AuthenticationResponse;
import se.typelias.dndbackend.models.User;
import se.typelias.dndbackend.util.JwtUtil;

@CrossOrigin(origins="/**")
@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @RequestMapping("/user")
    public UserDetails username(@RequestHeader(name = "Authorization") String token) {
        token = token.substring(7);
        return userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
    }

    @RequestMapping("/check/{token}")
    public boolean checkToken(@PathVariable String token) {
        return jwtTokenUtil.validateToken(token, userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

}
