package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.logicaAnotacoes.Injector;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(value = "/adicionarProdutos")
public class ProdutoAdicionarServlet implements Command {

	@Inject
	private ProdutoService produtoService;

	public ProdutoAdicionarServlet() {
		Injector.injectDependencies(this);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();

		if ("GET".equalsIgnoreCase(method)) {
			doGet(request, response);
		} else if ("POST".equalsIgnoreCase(method)) {
			doPost(request, response);
		}
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Encaminha para o formulário de produto
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
		dispatcher.forward(request, response);
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Captura os parâmetros enviados no formulário
		String nome = request.getParameter("nome");
		double preco = Double.parseDouble(request.getParameter("preco"));

		// Adiciona o produto via serviço
		produtoService.adicionarProduto(nome, preco);

		// Redireciona para a lista de produtos
		System.out.println("✨ Produto adicionado com sucesso: " + nome + " - R$" + preco);
		response.sendRedirect("listarProdutos");
	}
}
