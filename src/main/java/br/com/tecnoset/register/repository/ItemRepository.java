package br.com.tecnoset.register.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecnoset.register.entities.Item;
import br.com.tecnoset.register.entities.enuns.Descricao;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findByDescricao(Descricao descricao);
	

}
