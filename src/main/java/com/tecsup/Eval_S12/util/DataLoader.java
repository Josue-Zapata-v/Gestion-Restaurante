package com.tecsup.Eval_S12.util;

import com.tecsup.Eval_S12.entity.Role;
import com.tecsup.Eval_S12.entity.User;
import com.tecsup.Eval_S12.repository.RolRepository;
import com.tecsup.Eval_S12.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            // 1. Crear Roles si no existen
            Role admin = rolRepository.save(new Role(null, "ADMIN"));
            Role mozo = rolRepository.save(new Role(null, "MOZO"));
            Role cocinero = rolRepository.save(new Role(null, "COCINERO"));
            Role cajero = rolRepository.save(new Role(null, "CAJERO"));

            if (userRepository.findByUsername("admin").isEmpty()) {
                // 2. Crear User ADMIN (Contraseña: admin)
                User adminUser = new User();
                adminUser.setUsername("admin");
                // La contraseña se cifra con BCrypt
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setRoles(Stream.of(admin, mozo, cocinero, cajero).collect(Collectors.toSet()));
                userRepository.save(adminUser);

                System.out.println("User 'admin' creado con contraseña 'admin'");
            }
        }
    }
}