package br.com.tecnoset.register.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tecnoset.register.entities.enuns.Descricao;
import lombok.Getter;

@Getter
public class ItemDTO {

	@NotNull
	@Enumerated(EnumType.STRING)
	private Descricao descricao;
	@NotNull
	private Integer quantidade;
	@NotNull
	@NotEmpty
	private String ncm;

}
