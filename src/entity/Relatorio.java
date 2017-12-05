package entity;

import java.util.Date;

public class Relatorio {

	private String nomeCategoria;
	private static Date dataInicial;
	private static Date dataFinal;
	private int quantidadeSolicitada;
	private int quantidadeParaLocais;
	private int quantidadeDeferidas;
	private int quantidadeIndeferidas;
	private static int quantidadePatrimonios;
	private static int quantidadeUsados;
	private static int quantidadeLivres;

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public static Date getDataInicial() {
		return dataInicial;
	}

	public static void setDataInicial(Date dataInicial) {
		Relatorio.dataInicial = dataInicial;
	}

	public static Date getDataFinal() {
		return dataFinal;
	}

	public static void setDataFinal(Date dataFinal) {
		Relatorio.dataFinal = dataFinal;
	}

	public int getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(int quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public int getQuantidadeParaLocais() {
		return quantidadeParaLocais;
	}

	public void setQuantidadeParaLocais(int quantidadeParaLocais) {
		this.quantidadeParaLocais = quantidadeParaLocais;
	}

	public int getQuantidadeDeferidas() {
		return quantidadeDeferidas;
	}

	public void setQuantidadeDeferidas(int quantidadeDeferidas) {
		this.quantidadeDeferidas = quantidadeDeferidas;
	}

	public int getQuantidadeIndeferidas() {
		return quantidadeIndeferidas;
	}

	public void setQuantidadeIndeferidas(int quantidadeIndeferidas) {
		this.quantidadeIndeferidas = quantidadeIndeferidas;
	}

	public static int getQuantidadePatrimonios() {
		return quantidadePatrimonios;
	}

	public static void setQuantidadePatrimonios(int quantidadePatrimonios) {
		Relatorio.quantidadePatrimonios = quantidadePatrimonios;
	}

	public static int getQuantidadeUsados() {
		return quantidadeUsados;
	}

	public static void setQuantidadeUsados(int quantidadeUsados) {
		Relatorio.quantidadeUsados = quantidadeUsados;
	}

	public static int getQuantidadeLivres() {
		return quantidadeLivres;
	}

	public static void setQuantidadeLivres(int quantidadeLivres) {
		Relatorio.quantidadeLivres = quantidadeLivres;
	}

}
