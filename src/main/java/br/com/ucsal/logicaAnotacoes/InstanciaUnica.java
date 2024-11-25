package br.com.ucsal.logicaAnotacoes;

import br.com.ucsal.anotacoes.Singleton;

public class InstanciaUnica {

    @SuppressWarnings("unchecked")
    public static <T> T carregarSingleton(Class<T> clazz) {
        // Verifica se a classe está anotada com @Singleton
        if (clazz.isAnnotationPresent(Singleton.class)) {
            try {
                // Invoca o método "getInstancia" para obter a instância do singleton
                System.out.println("A classe " + clazz.getName() + " está anotada com @Singleton. Carregando instância...");
                return (T) clazz.getMethod("getInstancia").invoke(null);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Singleton para a classe " + clazz.getName(), e);
            }
        }
        
        // Caso a classe não seja anotada com @Singleton
        throw new IllegalArgumentException("A classe " + clazz.getName() + " não está anotada com @Singleton");
    }
}
