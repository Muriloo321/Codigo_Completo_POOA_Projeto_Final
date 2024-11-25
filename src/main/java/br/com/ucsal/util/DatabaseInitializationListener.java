package br.com.ucsal.util;

import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.MemoriaProdutoRepository;
import br.com.ucsal.persistencia.ProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseInitializationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Iniciando o banco de dados HSQLDB...");

		// Obtém a instância do repositório
		ProdutoRepository<Produto, Integer> produtoRepository = MemoriaProdutoRepository.getInstancia();

		// Passa o repositório para o serviço
		ProdutoService produtoService = new ProdutoService(produtoRepository);

		// Este método serve para adicionar um produto usando o serviço, neste caso estarei inicializando diversos produtos
		produtoService.adicionarProduto("Casca de Banana", 0.65);
		produtoService.adicionarProduto("Boneca da Barbie", 4300.00);
		produtoService.adicionarProduto("Teclado Externo USB", 72.90);
		produtoService.adicionarProduto("Maçã", 2);
		produtoService.adicionarProduto("Computador", 8999.99);
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Aplicação sendo finalizada.");
	}
}