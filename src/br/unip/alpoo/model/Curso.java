package br.unip.alpoo.model;

public class Curso {
	private int codCurso;
	private String NomeCurso; //(List com Administração de Empresas, BioMedicina, Ciências Biológicas, Ciencias da Computação, Direito, Educação Física, Farmacologia, Rede de Computadores, Sistemas de Informações,...), 
	private TipoCurso TipoCurso; //(RadioButton com Bacharel, Gestão, outros),
	private int CargaHorária;
	private int CodInstituto;
	
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	public String getNomeCurso() {
		return NomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		NomeCurso = nomeCurso;
	}
	public TipoCurso getTipoCurso() {
		return TipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		TipoCurso = tipoCurso;
	}
	public int getCargaHorária() {
		return CargaHorária;
	}
	public void setCargaHorária(int cargaHorária) {
		CargaHorária = cargaHorária;
	}
	public int getCodInstituto() {
		return CodInstituto;
	}
	public void setCodInstituto(int codInstituto) {
		CodInstituto = codInstituto;
	}
}
