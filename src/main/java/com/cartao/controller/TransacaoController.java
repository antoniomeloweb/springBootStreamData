package com.cartao.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.cartao.entity.Transacao;
import com.cartao.repository.TransacaoRepository;
import com.cartao.utils.Constants;
import com.cartao.utils.Utils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@ApiOperation(value = "Exibe lista de transações para determinado dia formato 'dd/MM/yyyy' em um arquivo no formato JSON",
		    notes = "Este método não deve ser utilizado para grandes arquivos, pois pode ocorrer timeout",
		    response = StreamingResponseBody.class,
		    responseContainer = "List")
	@RequestMapping(value="/getByDate/{dia}/{mes}/{ano}", method = RequestMethod.GET)
	public @ResponseBody Iterable<Transacao> getTransasacao(@PathVariable(value = "dia") String dia,
															@PathVariable(value = "mes") String mes,
															@PathVariable(value = "ano") String ano) throws Exception{
		String dataStr = dia + "/" + mes + "/" + ano;
		//se a data for invalida, sai do metodo
    	if(!Utils.validateDateString(dataStr)){
    		 throw new Exception(Constants.ERRO_DATA_INVALIDA + "["+dataStr+"]");
    	}
		
		return transacaoRepository.getTransacoesDoDia(dataStr);
		//return transacaoRepository.findAll();
	}
	
	@ApiOperation(value = "Exibe lista de transações para determinado dia formato 'dd/MM/yyyy' em um arquivo streaming",
		    notes = "Este método faz uma consulta paginada em bando de dados, evitando timeout na geração de grande arquivos",
		    response = StreamingResponseBody.class,
		    responseContainer = "List")
	@RequestMapping(value="getByDate/stream/{dia}/{mes}/{ano}", method = RequestMethod.GET, produces = "text/plain")
	public StreamingResponseBody getByDatePaging(@PathVariable(value = "dia") String dia,
			@PathVariable(value = "mes") String mes,
			@PathVariable(value = "ano") String ano){

			return new StreamingResponseBody() {
	            @Override
	            public void writeTo (OutputStream out) throws IOException {
	            	
	    			String dataStr = dia + "/" + mes + "/" + ano;
	    			//se a data for invalida, sai do metodo
	    	    	if(!Utils.validateDateString(dataStr)){
	    	    		 out.write((Constants.ERRO_DATA_INVALIDA).getBytes(Charset.forName("UTF-8")));
	    	    		 out.flush();
	    	    		 return;
	    	    	}
	            	
	            	Long count = transacaoRepository.getCountTransacoesDoDia(dataStr);
	            	
	            	//sai do metodo se não encontrou registros
	            	if(count==0){
	            		out.write((Constants.SEM_DADOS_TRANSACAO+dataStr).getBytes(Charset.forName("UTF-8")));
	            		out.flush();
	            		return;
	            	}
	            	
	            	Integer loops = Math.round(count/ Constants.TRANSACAO_PAGE_SIZE);
	                for (int i = 0; i <= loops; i++) {
	                	List<Transacao> lista = transacaoRepository.getTransacoesDoDiaPaginado(dataStr, i, Constants.TRANSACAO_PAGE_SIZE);
	                	for(Transacao t: lista){
	                		out.write((t.toStringArquivo()+"\n").getBytes(Charset.forName("UTF-8")));
	                	}
	                	out.flush();
	                    
	                    try {
	                        Thread.sleep(5);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        };
	    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody Transacao postTransasacao(@RequestBody Transacao transacao){
		
		return transacaoRepository.save(transacao);
	}
}
