package com.cartao.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class Constants {
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_FULL = "dd/MM/yyyy HH:mm:sss";
	public static final String DATE_RAW = "ddMMyyyy";
	public static DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	public static DateFormat DATE_FORMAT_FULL = new SimpleDateFormat(DATE_PATTERN_FULL);
	public static DateFormat RAW_DATE_FORMAT = new SimpleDateFormat(DATE_RAW);
	public static final Integer TRANSACAO_PAGE_SIZE = 2;
	public static final String SEM_DADOS_TRANSACAO = "Não foram encontradas transações para o dia: ";
	public static final String ERRO_DATA_INVALIDA = "A data informada é inválida.";
}
