package org.milan.models;

/**
 * Authentication response class
 *
 * @author Milan Rathod
 */
public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
