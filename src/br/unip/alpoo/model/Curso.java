package br.unip.alpoo.model;

public class Curso {
	private int codCurso;

	public Curso(int codCurso, NomesCursos nomeCurso, TipoCurso tipoCurso, int cargaHoraria, String codInstituto) {
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
		this.tipoCurso = tipoCurso;
		this.cargaHoraria = cargaHoraria;
		this.codInstituto = codInstituto;
	}

	private NomesCursos nomeCurso;
	private TipoCurso tipoCurso;
	private int cargaHoraria;
	private String codInstituto;

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public NomesCursos getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(NomesCursos nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHorária(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getCodInstituto() {
		return codInstituto;
	}

	public void setCodInstituto(String codInstituto) {
		this.codInstituto = codInstituto;
	}
}
