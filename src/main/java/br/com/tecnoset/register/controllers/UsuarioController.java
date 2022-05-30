package br.com.tecnoset.register.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecnoset.register.dto.UsuarioDTO;
import br.com.tecnoset.register.entities.Usuario;
import br.com.tecnoset.register.services.UsuarioService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		return ResponseEntity.ok().body(usuarioService.findAll());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		return new ResponseEntity<UsuarioDTO>(usuarioService.insert(usuarioDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioDTO usuarioDTO, @PathVariable Integer id) {
		return new ResponseEntity<UsuarioDTO>(usuarioService.update(usuarioDTO, id), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> validarSenha(@RequestBody @Valid Usuario usuario) {
		Boolean valid = usuarioService.validarSenha(usuario);
		if (!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
