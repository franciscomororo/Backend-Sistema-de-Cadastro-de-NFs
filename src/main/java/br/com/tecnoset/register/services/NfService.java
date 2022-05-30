package br.com.tecnoset.register.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecnoset.register.dto.NfFormDTO;
import br.com.tecnoset.register.entities.Nf;
import br.com.tecnoset.register.exceptions.MethodArgumentNotValidException;
import br.com.tecnoset.register.exceptions.ObjectNotFoundException;
import br.com.tecnoset.register.repository.NfRepository;

@Service
public class NfService {

	@Autowired
	private NfRepository nfRepository;

	public List<NfFormDTO> findAll() {
		List<Nf> nf = nfRepository.findAll();
		return nf.stream().map(NfFormDTO::new).collect(Collectors.toList());
	}

	public NfFormDTO insert(@Valid NfFormDTO nfDTO) {
		Nf nf = new Nf();
		nf.setNumero(nfDTO.getNumero());
		nf.setPlanta(nfDTO.getPlanta());
		nf.setItems(nfDTO.getItems());
		nfRepository.save(nf);
		return new NfFormDTO(nf);

	}

	public void delete(Integer id) {
		nfRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
	    nfRepository.deleteById(id);
	}

	public NfFormDTO update(@Valid NfFormDTO nfDTO, Integer id) {
		Nf nf = nfRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		try {
			nf.setNumero(nfDTO.getNumero());
			nf.setPlanta(nfDTO.getPlanta());
			nf.setItems(nfDTO.getItems());
			nfRepository.save(nf);
			return new NfFormDTO(nf);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}

	}

}
