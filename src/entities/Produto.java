package entities;

public class Produto {
	private String produto;
	private Long quantidade;
	private Double valor;

	public Produto() {
	}

	public Produto(String produto, Long quantidade, Double valor) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double valorTotal() {
		return quantidade * valor;
	}

	@Override
	public String toString() {
		return produto + "," + quantidade + "," + valor;
	}

}
