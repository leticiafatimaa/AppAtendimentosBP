package controle;

public class PrevisaoReceitaDTO {
	
	private String idAtendimento;
	private String valor;
	private String cpfcnpj;
	private String nome;
	private String razaoSocial;
	
	public PrevisaoReceitaDTO(String idAtendimento, String valor, String cpfcnpj, String nome, String razaoSocial) {
		this.idAtendimento = idAtendimento;
		this.valor = valor;
		this.cpfcnpj = cpfcnpj;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
	}
	public String getIdAtendimento() {
		return idAtendimento;
	}
	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}
