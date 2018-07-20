package com.cartao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cartao.entity.Transacao;
import com.cartao.repository.TransacaoRepository;


public class TransacaoTest {
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	private static Long NUM_CARTAO_MOCK = 110011100044L;
	private static BigDecimal VALOR_MOCK = new BigDecimal("100000");
	private static Date DATE_MOCK = new Date();
	
	@Test
	public void saveTransacao(){
		Transacao t1 = createTransacaoObj();
		
		Transacao tSaved = transacaoRepository.save(t1);
		assertEquals("Transação não foi salva.", t1.getCartao(),tSaved.getCartao());
		
	}
	
	@Test 
	public void loadTransacao(){
		Transacao t1 = transacaoRepository.save(createTransacaoObj());
		Transacao tLoaded = transacaoRepository.findOne(t1.getId());
		
		assertEquals("Transação não foi localizada",t1, tLoaded);
	}
	
	private Transacao createTransacaoObj(){
		Transacao t1 = new Transacao();
		t1.setData(DATE_MOCK);
		t1.setValor(VALOR_MOCK);
		t1.setCartao(NUM_CARTAO_MOCK);
		
		return t1;
	}
}
