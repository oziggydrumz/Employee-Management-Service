package dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SigningRequest {
    private long userId;
    private String name;
    private LocalTime signIn;
    private String signOut;

}
