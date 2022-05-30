package br.com.tecnoset.register.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tecnoset.register.entities.Item;
import br.com.tecnoset.register.entities.enuns.Descricao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNfDTO {

	@NotNull
	@Enumerated(EnumType.STRING)
	private Descricao descricao;
	@NotNull
	private Integer quantidade;
	@NotNull
	@NotEmpty
	private String ncm;
	@NotNull
	private List<NfDTO> nfsDTO;

	public ItemNfDTO(Item item) {
		this.ncm = item.getNcm();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.nfsDTO = item.getNfs().stream().map(NfDTO::new).collect(Collectors.toList());
	}
	
	

}
