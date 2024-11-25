package br.com.ucsal.controller;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/view/*", "/"}) // Mapeamento para capturar qualquer caminho dentro de "/view"
public class ProdutoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Map<String, Command> commands;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        // Recupera o mapa de comandos a partir do contexto da aplicação
        this.commands = (Map<String, Command>) getServletContext().getAttribute("commands");
        
        if (this.commands == null) {
            System.out.println("Erro: Mapa de comandos não encontrado no contexto da aplicação.");
        } else {
            System.out.println("Mapa de comandos carregado com sucesso.");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pega o caminho completo da requisição (ex: /seu-app/view/adicionarProdutos)
        String requestURI = request.getRequestURI();
        
        // Pega o contexto da aplicação (ex: /seu-app)
        String contextPath = request.getContextPath();

        // Remove o contexto da URL para obter apenas o caminho da requisição após o contexto
        String path = requestURI.substring(contextPath.length());

        // Se o caminho estiver vazio ou for apenas "/"
        if (path == null || path.equals("/")) {
            path = "/listarProdutos"; // Redireciona para a página de listagem
        }

        System.out.println("Caminho solicitado: " + path);

        // Verifica se o mapa de comandos está presente no contexto
        if (this.commands == null) {
            System.out.println("Erro: Mapa de comandos não encontrado no contexto durante a requisição.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro no servidor ao processar a requisição.");
            return;
        }

        // Verifica se há um comando associado ao caminho solicitado
        Command command = commands.get(path);

        if (command != null) {
            // Executa o comando se encontrado
            System.out.println("Executando comando para o caminho: " + path);
            command.execute(request, response);
        } else {
            // Caso o comando não seja encontrado, gera um erro 404
            System.out.println("Comando não encontrado para o caminho: " + path);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada para o caminho: " + path);
        }
    }
}
