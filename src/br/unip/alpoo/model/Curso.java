package br.unip.alpoo.model;

public class Curso {
	private int codCurso;
	private NomesCursos NomeCurso; //(List com Administra��o de Empresas, BioMedicina, Ci�ncias Biol�gicas, Ciencias da Computa��o, Direito, Educa��o F�sica, Farmacologia, Rede de Computadores, Sistemas de Informa��es,...), 
	private TipoCurso TipoCurso; //(RadioButton com Bacharel, Gest�o, outros),
	private int CargaHoraria;
	private int CodInstituto;
	
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	public NomesCursos getNomeCurso() {
		return NomeCurso;
	}
	public void setNomeCurso(NomesCursos nomeCurso) {
		NomeCurso = nomeCurso;
	}
	public TipoCurso getTipoCurso() {
		return TipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		TipoCurso = tipoCurso;
	}
	public int getCargaHoraria() {
		return CargaHoraria;
	}
	public void setCargaHor�ria(int cargaHoraria) {
		CargaHoraria = cargaHoraria;
	}
	public int getCodInstituto() {
		return CodInstituto;
	}
	public void setCodInstituto(int codInstituto) {
		CodInstituto = codInstituto;
	}
}
