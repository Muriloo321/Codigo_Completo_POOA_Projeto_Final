package br.com.ucsal.service;

import java.util.List;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.ProdutoRepository;

public class ProdutoService {

	private final ProdutoRepository<Produto, Integer> produtoRepository;

	public ProdutoService(ProdutoRepository<Produto, Integer> repository) {
		this.produtoRepository = repository;
	}

	public void adicionarProduto(String nome, double preco) {
		Produto produto = new Produto(null, nome, preco);
		produtoRepository.adicionar(produto);
	}

	public void removerProduto(Integer id) {
		produtoRepository.remover(id);
	}

	public Produto obterProdutoPorId(Integer id) {
		return produtoRepository.obterPorID(id);
	}

	public void atualizarProduto(Produto produto) {
		produtoRepository.atualizar(produto);
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.listar();
	}
}
