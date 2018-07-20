package com.cartao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cartao.entity.Transacao;

public interface TransacaoRepository  extends CrudRepository<Transacao, Long>{

	@Query(nativeQuery = true, value="select * from transacao where DATE_FORMAT(data,'%d/%m/%Y') = ?")
	List<Transacao> getTransacoesDoDia(String dt);
	
	@Query(nativeQuery = true, value="select count(*) from transacao where DATE_FORMAT(data,'%d/%m/%Y') = ?")
	Long getCountTransacoesDoDia(String dt);

	@Query(nativeQuery = true, value="select * from transacao where DATE_FORMAT(data,'%d/%m/%Y') = ? order by data asc LIMIT ?,?")
	List<Transacao> getTransacoesDoDiaPaginado(String dt, int numPage, int pageSize);
}
