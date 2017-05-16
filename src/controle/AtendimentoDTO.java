package controle;
import java.io.Serializable;

public class AtendimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 2825671152160967184L;
	
	private String id;
	private String numero; 
	private String status;
	private String titulo;
	private String deUnidade;
	private String regional;
	private String municipio;
	private String cnpjCliente;
	private String razaosocial;
	private String fantasia;
	private String numeroFuncionarios;
	private String produtoRegional;
	private Double vlrEconomico;
	private Double vlrFinanceiro;
	private String deApl;
	private String deSetor;
	private String dataEmissao;
	private String dataInicio;
	private String produtividade;
	private String movimentacao;
	private String qualidade;
	private String retornoPrograma;
	private String cnae;
	
	private String dataAceite;
	private String dataConclusao;
	
	public AtendimentoDTO(
			String id,
			String numero, 
			String status,
			String titulo,
			String deUnidade,
			String regional,
			String municipio,
			String cnpjCliente,
			String razaosocial,
			String fantasia,
			String numeroFuncionarios,
			String produtoRegional,
			String deApl,
			Double vlrFinanceiro,
			Double vlrEconomico,
			String deSetor,
			String dataEmissao,
			String dataInicio,
			String produtividade,
			String movimentacao,
			String qualidade,
			String retornoPrograma,
			String cnae,
			String dataAceite,
			String dataConclusao
			) {
		
		this.setId( id );
		this.setNumero(numero);
		this.setStatus(status);
		this.setTitulo(titulo);
		this.setDeUnidade(deUnidade);
		this.setRegional(regional);
		this.setCnpjCliente(cnpjCliente);
		this.setRazaoSocial(razaosocial);
		this.setFantasia(fantasia);
		this.setNumeroFuncionarios(numeroFuncionarios);
		this.setProdutoRegional(produtoRegional);
		this.setVlrEconomico(vlrEconomico);
		this.setVlrFinanceiro(vlrFinanceiro);
		this.setDeApl(deApl);
		this.setDeSetor(deSetor);
		this.setDataEmissao(dataEmissao);
		this.setDataInicio(dataInicio);
		this.setProdutividade(produtividade);
		this.setMovimentacao(movimentacao);
		this.setQualidade(qualidade);
		this.setRetornoPrograma(retornoPrograma);
		this.setCnae(cnae);
		this.setMunicipio(municipio);
		this.setDataAceite(dataAceite);
		this.setDataConclusao(dataConclusao);
	}


	public String getNumero() {
		return numero == null ? "" : numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo == null ? "" : titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDeUnidade() {
		return deUnidade == null ? "" : deUnidade;
	}
	public void setDeUnidade(String deUnidade) {
		this.deUnidade = deUnidade;
	}
	public String getRegional() {
		return regional == null ? "" : regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getMunicipio(){
		return municipio == null ? "" : municipio;
	}
	public void setMunicipio(String municipio){
		this.municipio = municipio;
	}
	public String getCnpjCliente() {
		return cnpjCliente == null ? "" : cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getRazaoSocial() {
		return razaosocial == null ? "" : razaosocial;
	}
	public void setRazaoSocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getNumeroFuncionarios() {
		return numeroFuncionarios == null ? "" : numeroFuncionarios;
	}
	public void setNumeroFuncionarios(String numeroFuncionarios) {
		this.numeroFuncionarios = numeroFuncionarios;
	}
	public String getProdutoRegional() {
		return produtoRegional == null ? "" : produtoRegional;
	}
	public void setProdutoRegional(String produtoRegional) {
		this.produtoRegional = produtoRegional;
	}
	public Double getVlrEconomico() {
		return vlrEconomico == null ? 0.0 : vlrEconomico;
	}
	public void setVlrEconomico(Double vlrEconomico) {
		this.vlrEconomico = vlrEconomico;
	}
	public Double getVlrFinanceiro() {
		return vlrFinanceiro == null ? 0.0 : vlrFinanceiro;
	}
	public void setVlrFinanceiro(Double vlrFinanceiro) {
		this.vlrFinanceiro = vlrFinanceiro;
	}
	public String getFantasia() {
		return fantasia == null ? "" : fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getDeApl() {
		return deApl == null ? "" : deApl;
	}
	public void setDeApl(String deApl) {
		this.deApl = deApl;
	}
	public String getDeSetor() {
		return deSetor == null ? "" : deSetor;
	}
	public void setDeSetor(String deSetor) {
		this.deSetor = deSetor;
	}
	public String getDataEmissao() {
		return dataEmissao == null ? "" : dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataInicio() {
		return dataInicio == null ? "" : dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getRazaosocial() {
		return razaosocial == null ? "" : razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getProdutividade() {
		return produtividade == null ? "" : produtividade;
	}
	public void setProdutividade(String produtividade) {
		this.produtividade = produtividade;
	}
	public String getMovimentacao() {
		return movimentacao == null ? "" : movimentacao;
	}
	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}
	public String getQualidade() {
		return qualidade == null ? "" : qualidade;
	}
	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}
	public String getRetornoPrograma() {
		return retornoPrograma == null ? "" : retornoPrograma;
	}
	public void setRetornoPrograma(String retornoPrograma) {
		this.retornoPrograma = retornoPrograma;
	}
	public String getCnae() {
		return cnae == null ? "" : cnae;
	}
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	public String getDataAceite() {
		return dataAceite == null ? "" : dataAceite;
	}
	public void setDataAceite(String dataAceite) {
		this.dataAceite = dataAceite;
	}
	public String getDataConclusao() {
		return dataConclusao == null ? "" : dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getId() {
		return id == null ? "" : id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}

