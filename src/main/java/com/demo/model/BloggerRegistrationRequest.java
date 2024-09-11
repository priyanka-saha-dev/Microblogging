package com.demo.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloggerRegistrationRequest {
    private String username;
    private String password;
}
