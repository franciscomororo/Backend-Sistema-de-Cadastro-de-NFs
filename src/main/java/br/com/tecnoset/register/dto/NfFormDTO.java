package br.com.tecnoset.register.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tecnoset.register.entities.Item;
import br.com.tecnoset.register.entities.Nf;
import br.com.tecnoset.register.entities.enuns.Planta;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NfFormDTO {
	
	@NotNull
	@NotEmpty
	private String numero;
	@NotNull
	@NotEmpty
	private List<Item> items;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Planta planta;
	
			
	public NfFormDTO (Nf nf) {
		this.numero = nf.getNumero();
		this.items = nf.getItems();
		this.planta = nf.getPlanta();
	}

}
