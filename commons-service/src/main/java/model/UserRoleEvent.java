package model;

import lombok.Data;

import java.util.List;
@Data
public class UserRoleEvent {

    private Long userId;
    private List<String>roles;
}
