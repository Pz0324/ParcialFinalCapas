package com.uca.parcialfinalncapas.config;

import com.uca.parcialfinalncapas.dto.request.UserCreateRequest;
import com.uca.parcialfinalncapas.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        UserCreateRequest userDto = UserCreateRequest.builder()
                .nombre("Usuario Default")
                .correo("usuario@correo.com")
                .password("1234")
                .nombreRol("USER")
                .build();
        UserCreateRequest techDto = UserCreateRequest.builder()
                .nombre("Tecnico Default")
                .correo("tecnico@correo.com")
                .password("1234")
                .nombreRol("TECH")
                .build();

        userService.save(userDto);
        userService.save(techDto);

        System.out.println("Usuarios por defecto creados.");
    }
}
