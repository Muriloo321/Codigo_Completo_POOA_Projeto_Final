package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.logicaAnotacoes.Injector;
import br.com.ucsal.model.Produto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(value = "/editarServlets")
public class ProdutoEditarServlet implements Command {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoService produtoService;

    public ProdutoEditarServlet() {
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("produto", produtoService.obterProdutoPorId(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
        dispatcher.forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        produtoService.atualizarProduto(new Produto(id, nome, preco));
        response.sendRedirect("listarProdutos");
    }
}

