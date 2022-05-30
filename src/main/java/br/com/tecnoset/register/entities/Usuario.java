package br.com.tecnoset.register.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	@NotBlank(message = "O e-mail é obrigatório")
	@NotNull
	@NotEmpty
	@Email(message = "Insira um e-mail válido")
	@Column(name ="email", unique = true)
	private String email;
	@NotBlank(message = "A senha é obrigatória")
	private String senha;
		

	
}
