package br.com.tecnoset.register.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.tecnoset.register.dto.UsuarioDTO;
import br.com.tecnoset.register.entities.Usuario;
import br.com.tecnoset.register.exceptions.MethodArgumentNotValidException;
import br.com.tecnoset.register.exceptions.ObjectNotFoundException;
import br.com.tecnoset.register.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

	public UsuarioDTO insert(@Valid UsuarioDTO usuarioDTO) {
		String encoder = this.passwordEncoder.encode(usuarioDTO.getSenha());
		usuarioDTO.setSenha(encoder);
		try {
			Usuario usuario = new Usuario();
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setNome(usuarioDTO.getNome());
			usuario.setSenha(usuarioDTO.getSenha());
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
		
	}

	public void delete(Integer id) {
		usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		usuarioRepository.deleteById(id);
	}

	public UsuarioDTO update(@Valid UsuarioDTO usuarioDTO, Integer id) {
		String encoder = this.passwordEncoder.encode(usuarioDTO.getSenha());
		usuarioDTO.setSenha(encoder);
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		try {
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setNome(usuarioDTO.getNome());
			usuario.setSenha(usuarioDTO.getSenha());
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(e.getMessage());
		}
	}
	public Boolean validarSenha(@Valid Usuario usuario) {
		String senha = usuarioRepository.getById(usuario.getId()).getSenha();
		Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
		return valid;
	}

}
