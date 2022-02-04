package com.jingzhe.authentication.model;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class AccessToken {
    String id;
    String user;
    String group;

    Instant expiration;
    Instant issuedAt;
}
