package br.com.tecnoset.register.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecnoset.register.dto.ItemNfDTO;
import br.com.tecnoset.register.entities.enuns.Descricao;
import br.com.tecnoset.register.services.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin("http://localhost:4200")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<Integer> sumPerItem(@RequestParam(required = false, name = "descricao") Descricao descricao) {
		return new ResponseEntity<Integer>(itemService.sumPerItem(descricao), HttpStatus.OK);
	}

	@GetMapping("/nf")
	public ResponseEntity<List<ItemNfDTO>> ItemNf(
			@RequestParam(required = false, name = "descricao") Descricao descricao) {
		return new ResponseEntity<List<ItemNfDTO>>(itemService.ItemNf(descricao), HttpStatus.OK);
	}

	@GetMapping("/nfs")
	public ResponseEntity<List<ItemNfDTO>> ItemsNfDTOs() {
		return new ResponseEntity<List<ItemNfDTO>>(itemService.ItemsNfDTOs(), HttpStatus.OK);
	}

	@DeleteMapping("/{idItem}/{idNf}")
	@Transactional
	public ResponseEntity<Void> delete(@RequestParam(required = true, name = "quantidade") Integer quantidade,
			@PathVariable Integer idItem, @PathVariable Integer idNf) {
		itemService.deleteByQuantidade(quantidade, idItem, idNf);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
