package com.cartao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cartao.utils.Constants;
import com.cartao.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long cartao;
    private BigDecimal valor;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date data;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCartao() {
		return cartao;
	}
	public void setCartao(Long cartao) {
		this.cartao = cartao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "cartao:" + cartao + ", valor:" + valor + ", data:" + data;
	}
	
    public String toStringArquivo(){
    	String str="";
    	//formata o numero do cartao
    	str += String.format("%016d", cartao)+" "; 
    	//formata o valor
    	str += Utils.padLeft(valor.toString().replace(".", "").replace(",", ""),11,'0')+" ";
    	//formata data
    	str += Constants.RAW_DATE_FORMAT.format(data);
    	
    	return str;
    }
    
}
