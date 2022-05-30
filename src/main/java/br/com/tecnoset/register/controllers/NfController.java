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

import br.com.tecnoset.register.dto.NfFormDTO;
import br.com.tecnoset.register.services.NfService;

@RestController
@RequestMapping("/nfs")
@CrossOrigin("http://localhost:4200")
public class NfController {
	
	@Autowired
	private NfService nfService;
	
	@GetMapping
	public ResponseEntity<List<NfFormDTO>> findAll() {
		return new ResponseEntity<List<NfFormDTO>>(nfService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<NfFormDTO> insert(@RequestBody @Valid NfFormDTO nfDTO) {
		return new ResponseEntity<NfFormDTO>(nfService.insert(nfDTO), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		nfService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<NfFormDTO> update(@RequestBody @Valid NfFormDTO nfDTO, @PathVariable Integer id) {
		return new ResponseEntity<NfFormDTO>(nfService.update(nfDTO, id), HttpStatus.CREATED);
	}
	
}
