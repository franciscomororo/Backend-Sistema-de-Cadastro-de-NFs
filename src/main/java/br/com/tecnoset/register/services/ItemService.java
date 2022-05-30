package br.com.tecnoset.register.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecnoset.register.dto.ItemNfDTO;
import br.com.tecnoset.register.entities.Item;
import br.com.tecnoset.register.entities.Nf;
import br.com.tecnoset.register.entities.enuns.Descricao;
import br.com.tecnoset.register.exceptions.InvalidQuantityException;
import br.com.tecnoset.register.exceptions.ObjectNotFoundException;
import br.com.tecnoset.register.repository.ItemRepository;
import br.com.tecnoset.register.repository.NfRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private NfRepository nfRepository;

	ItemNfDTO itemNfDTO;
	List<ItemNfDTO> itemsNfDTOs = new ArrayList<ItemNfDTO>();

	public Integer sumPerItem(Descricao descricao) {
		List<Item> items = itemRepository.findByDescricao(descricao);
		return items.stream().mapToInt(i -> i.getQuantidade()).sum();
	}

	public List<ItemNfDTO> ItemNf(Descricao descricao) {
		List<Item> items = itemRepository.findByDescricao(descricao);
		return items.stream().map(ItemNfDTO::new).collect(Collectors.toList());

	}

	private void adicionaLista(Integer quantidade, Item item, Nf nf) {
		Item itemLista = new Item();
		Boolean existe = false;
		Descricao descricao = item.getDescricao();
		String numeroNf = nf.getNumero();
		for (int i = 0; i < itemsNfDTOs.size(); i++) {
			if (itemsNfDTOs.get(i).getNfsDTO().get(0).getNumero().equals(numeroNf)
					&& itemsNfDTOs.get(i).getDescricao().equals(descricao)) {
				existe = true;
				itemsNfDTOs.get(i).setQuantidade(itemsNfDTOs.get(i).getQuantidade() + quantidade);
			}
		}
		if (!existe) {
			itemLista.setQuantidade(quantidade);
			itemLista.setDescricao(item.getDescricao());
			itemLista.setNcm(item.getNcm());
			itemLista.setNfs(item.getNfs());
			itemNfDTO = new ItemNfDTO(itemLista);
			itemsNfDTOs.add(itemNfDTO);
		}

	}

	public void deleteByQuantidade(Integer quantidade, Integer idItem, Integer idNf) {
		Item item = itemRepository.findById(idItem).orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		Nf nf = nfRepository.findById(idNf).orElseThrow(() -> new ObjectNotFoundException("ID não encontrado"));
		Integer index = nf.getItems().indexOf(item);
		if (index != -1) {
			if (nf.getItems().get(index).getQuantidade() < quantidade) {
				throw new InvalidQuantityException("Quantidade Insuficiente");
			}
			if (item.getQuantidade() == quantidade) {
				nf.getItems().remove(nf.getItems().get(index));
				itemRepository.deleteById(idItem);
				if (nf.getItems().size() == 0) {
					nfRepository.deleteById(idNf);
				}
			} else if (nf.getItems().get(index).getQuantidade() > quantidade) {
				nf.getItems().get(index).setQuantidade(item.getQuantidade() - quantidade);
			}
			adicionaLista(quantidade, item, nf);
		}
	}

	public List<ItemNfDTO> ItemsNfDTOs() {
		return itemsNfDTOs;
	}
}
