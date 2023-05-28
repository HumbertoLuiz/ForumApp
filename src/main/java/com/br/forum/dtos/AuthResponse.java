package com.br.forum.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthResponse {

	private String email;

	private String accessToken;

    public AuthResponse(String email, String accessToken) {
        this.email= email;
        this.accessToken= accessToken;
    }

}
