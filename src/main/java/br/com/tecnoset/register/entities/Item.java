package br.com.tecnoset.register.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecnoset.register.entities.enuns.Descricao;
import lombok.Data;

@Entity
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotEmpty
	private String ncm;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Descricao descricao;
	@NotNull
	private Integer quantidade;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "nf_item", joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "nf_id"))
	private List<Nf> nfs;

}
