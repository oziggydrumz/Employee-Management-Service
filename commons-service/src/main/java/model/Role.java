package model;

import lombok.Getter;

@Getter
public enum Role {
    USER("user"),
    ADMIN("administrator");
    private final String value;

    Role(String value) {
        this.value = value;
    }
}
