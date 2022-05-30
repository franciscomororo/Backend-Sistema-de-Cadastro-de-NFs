package br.com.tecnoset.register.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tecnoset.register.entities.Nf;
import br.com.tecnoset.register.entities.enuns.Planta;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NfDTO {
	
	@NotNull
	@NotEmpty
	private String numero;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Planta planta;
	
			
	public NfDTO (Nf nf) {
		this.numero = nf.getNumero();
		this.planta = nf.getPlanta();
	}

}
