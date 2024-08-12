package com.example.api_user.unit;

import com.example.api_user.controllers.UserController;
import com.example.api_user.entities.Users;
import com.example.api_user.exceptionHandler.ResourceNotFoundException;
import com.example.api_user.reposiories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class TestesBuscarTodosUsuarios {

        @Test
        void deveRetornarTodosOsUsuariosQuandoElesExistirem() {
            // Dado (Given)
            Users usuario1 = new Users(1L, "Alice", "alice@example.com", null);
            Users usuario2 = new Users(2L, "Bob", "bob@example.com", null);
            when(userRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

            // Quando (When)
            List<Users> resultado = userController.findAll();

            // Então (Then)
                    assertThat(resultado).hasSize(2);
            assertThat(resultado).containsExactlyInAnyOrder(usuario1, usuario2);
        }

        @Test
        void deveLancarExcecaoQuandoNaoExistiremUsuarios() {
            // Dado (Given)
            when(userRepository.findAll()).thenReturn(Collections.emptyList());

            // Quando e Então (When & Then)
            assertThatThrownBy(() -> userController.findAll())
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Nenhum usuário encontrado.");
        }
    }
}
