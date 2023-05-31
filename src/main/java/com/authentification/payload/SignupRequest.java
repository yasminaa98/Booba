package com.authentification.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    private Long user_id ;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname ;

    @NotBlank
    private String email;
    @NotBlank
    private String homeAddress ;
    @NotBlank
    private int phone ;
    @NotBlank
    private String avgResponseTime;
    @NotBlank
    private String description;


}
