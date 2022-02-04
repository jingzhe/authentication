package com.jingzhe.authentication.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@Value
public class JwksResponse {

    @Schema(
            required = true,
            description = "Array of JWK values"
    )
    @NonNull
    List<JwkValue> keys;

}
