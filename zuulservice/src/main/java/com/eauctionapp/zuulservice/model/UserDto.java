package com.eauctionapp.zuulservice.model;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    private String username;
    private String password;
}
