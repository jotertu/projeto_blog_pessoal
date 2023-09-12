package controller;

import com.blogPessoal.blogPessoal.model.Usuario;
import com.blogPessoal.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start() {

        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "João da Silva", "Joao@email.com.br", "12345678", "Https://i.imgur.com/FETvs20.jpg"));
        usuarioRepository.save(new Usuario(0L, "Maria Silva", "Maria@email.com.br", "senha123", "Https://i.imgur.com/abc123.jpg"));
        usuarioRepository.save(new Usuario(0L, "Pedro Silva", "Pedro@email.com.br", "minhasenha", "Https://i.imgur.com/xyz456.jpg"));
        usuarioRepository.save(new Usuario(0L, "Ana Pereira", "Ana@email.com.br", "outrasenha", "Https://i.imgur.com/def789.jpg"));

    }

    @Test
    @DisplayName("Retorna 1 usuário")
    public void deveRetornarUmUsuario() {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("Joao@email.com.br");
        assertTrue(usuario.isPresent());
        assertEquals("João da Silva", usuario.get().getNome());

    }

    @Test
    @DisplayName("Retorna 3 usuários")
    public void deveRetornarTresUsuarios() {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
        assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Maria Silva"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Pedro Silva"));

    }

    @AfterAll
    public void end() {
        usuarioRepository.deleteAll();
    }
}
