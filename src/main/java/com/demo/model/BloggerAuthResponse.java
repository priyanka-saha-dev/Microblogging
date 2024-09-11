package com.demo.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloggerAuthResponse {
    private String token;
}
