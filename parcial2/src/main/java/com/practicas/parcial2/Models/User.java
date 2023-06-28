package com.practicas.parcial2.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    @NotNull(message = "name can't be null")
    private String name;

    @NotNull(message = "lastname can't be null")
    private String lastName;

    private String userName;
    private String password;
}
