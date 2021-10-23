package com.sha.springbootbookseller.controllers;

import com.sha.springbootbookseller.model.User;
import com.sha.springbootbookseller.services.IAuthenticationService;
import com.sha.springbootbookseller.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author sa
 * @date 3.07.2021
 * @time 19:47
 */
@RestController
@RequestMapping("api/authentication")//pre-path
@CrossOrigin
public class AuthenticationController
{
    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @PostMapping("sign-up") //api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
