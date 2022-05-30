package br.com.tecnoset.register.entities;

import java.util.List;

import javax.persistence.CascadeType;
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

import br.com.tecnoset.register.entities.enuns.Planta;
import lombok.Data;

@Entity
@Data
public class Nf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotEmpty
	private String numero;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Planta planta;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "nf_item", joinColumns = @JoinColumn(name = "nf_id"), 
	inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items;
}
