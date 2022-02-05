package com.jingzhe.authentication.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwksResponse {
    @Schema(
            required = true,
            description = "Array of JWK values"
    )
    @NonNull
    List<JwkValue> keys;

}
