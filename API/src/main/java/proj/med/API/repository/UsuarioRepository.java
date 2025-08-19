package proj.med.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.med.API.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
      
    Optional<Usuario> findByLogin(String login);
}