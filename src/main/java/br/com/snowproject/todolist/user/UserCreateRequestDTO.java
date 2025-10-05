package br.com.snowproject.todolist.user;

import lombok.Data;

@Data
public class UserCreateRequestDTO {

    private String username;
    private String name;
    private String password;

}