package com.jingzhe.authentication.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwkValue {

    @Schema(
            required = true,
            description = "Key type",
            example =  "RSA"
    )
    @NonNull
    private String kty;

    @Schema(
            required = true,
            description = "Key value exponent (base64 encoded)"
    )
    @NonNull
    private String e;

    @Schema(
            required = true,
            description = "Key value modulus (base64 encoded)"
    )
    @NonNull
    private String n;

    @Schema(
            required = true,
            description = "Key id"
    )
    @NonNull
    private String kid;

    @Schema(
            description = "Algorithm intended for use with the key",
            example =  "RSA-OAEP"
    )
    private String alg;

    @Schema(
            description = "Intended use of the public key",
            example = "`sig` or `enc`"
    )
    private String use;

}