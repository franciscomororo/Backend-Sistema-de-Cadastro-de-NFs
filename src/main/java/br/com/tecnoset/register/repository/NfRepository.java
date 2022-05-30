package br.com.tecnoset.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecnoset.register.entities.Nf;

@Repository
public interface NfRepository extends JpaRepository<Nf, Integer> {

}
