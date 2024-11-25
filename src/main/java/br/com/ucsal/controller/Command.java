package br.com.ucsal.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Essa interface serve para comandos que executam ações específicas em requisições, tipo GET, POST... Elas irão aparecer pelo código.
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
