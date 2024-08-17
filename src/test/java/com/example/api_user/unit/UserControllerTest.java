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
import java.util.Optional;

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
    class DadoBuscarTodosUsuarios {

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

    @Nested
    class DadoChamarOEndPointDeBuscaPorId {

        private Long id;
        private Users usuario;

        @BeforeEach
        void prepararDados() {
            id = 2L;
            usuario = new Users(id, "Alice", "alice@example.com", null);
        }

        @Nested
        class QuandoUsuarioExistir {

            @BeforeEach
            void configurarMock() {
                when(userRepository.findById(id)).thenReturn(Optional.of(usuario));
            }

            @Test
            void entaoDeveRetornarOUsuarioComIDCorreto() {
                // Quando (When)
                Users resultado = userController.findById(id);
                System.out.println("Retorno: " + resultado);

                // Então (Then)
                assertThat(resultado).isEqualTo(usuario);
                assertThat(resultado.getId()).isEqualTo(id);  // Verifica se o ID retornado é o esperado
            }
        }


        @Test
        void QuandoUsuarioNaoExistir_EntaoDeveLancarExcecao() {
            // Dado (Given)
            Long id = 1L;
            when(userRepository.findById(id)).thenReturn(Optional.empty());

            // Quando e Então (When & Then)
            assertThatThrownBy(() -> userController.findById(id))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Usuário não encontrado com ID: " + id);
        }
    }
    @Nested
    class TestesCriarUsuario {

        private Users usuarioParaCriar;
        private Users usuarioCriado;

        @BeforeEach
        void prepararDados() {
            // Inicializa os objetos que serão usados nos testes
            usuarioParaCriar = new Users(null, "Alice", "alice@example.com", null);
            usuarioCriado = new Users(1L, "Alice", "alice@example.com", null);
        }

        @Test
        void deveCriarUsuarioComSucesso() {
            // Configura o mock do repositório
            when(userRepository.save(usuarioParaCriar)).thenReturn(usuarioCriado);

            // Executa o método de criação
            Users resultado = userController.create(usuarioParaCriar);
            System.out.println("Retorno: " + resultado);

            // Verifica se o usuário foi criado corretamente
            assertThat(resultado).isNotNull();
            assertThat(resultado.getId()).isEqualTo(1L);
            assertThat(resultado.getName()).isEqualTo("Alice");
            assertThat(resultado.getEmail()).isEqualTo("alice@example.com");

            // Verifica se o repositório foi chamado corretamente
            verify(userRepository, times(1)).save(usuarioParaCriar);
        }
    }
}