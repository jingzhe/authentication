package com.jingzhe.authentication.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthenticationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8218805720229031405L;
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}
