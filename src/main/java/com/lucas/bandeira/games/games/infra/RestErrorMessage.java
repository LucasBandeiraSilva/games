package com.lucas.bandeira.games.games.infra;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class RestErrorMessage {
    private Instant data;
    private Integer httpStatus;
    private String error;
    private String message;
    private String path;

}
