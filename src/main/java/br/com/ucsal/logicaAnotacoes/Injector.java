package br.com.ucsal.logicaAnotacoes;

import java.lang.reflect.Field;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.PersistenciaFactory;
import br.com.ucsal.persistencia.ProdutoRepository;
import br.com.ucsal.service.ProdutoService;

public class Injector {

    @SuppressWarnings("unchecked")
    public static void injectDependencies(Object target) {
        Class<?> clazz = target.getClass();

        // Itera sobre todos os campos da classe
        for (Field field : clazz.getDeclaredFields()) {
            // Verifica se o campo possui a anotação @Inject
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true); // Torna o campo acessível

                try {
                    Object dependency = null;

                    // Injeta dependência com base no tipo do campo
                    if (field.getType().equals(ProdutoService.class)) {
                        // Determina o tipo de repositório dinamicamente
                        int repositoryType = PersistenciaFactory.MEMORIA; // ou MEMORIA, dependendo do contexto
                        ProdutoRepository<Produto, Integer> repository =
                                (ProdutoRepository<Produto, Integer>) PersistenciaFactory.getProdutoRepository(repositoryType);

                        // Cria uma instância do ProdutoService com o repositório apropriado
                        dependency = new ProdutoService(repository);
                    } else {
                        throw new IllegalArgumentException(
                                "Tipo de dependência não suportado para injeção: " + field.getType().getName()
                        );
                    }

                    // Injeta a dependência no campo
                    field.set(target, dependency);
                    System.out.println("Injeção bem-sucedida! Dependência injetada no campo: " + field.getName()); // Mensagem personalizada
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao injetar a dependência no campo: " + field.getName(), e);
                } catch (Exception e) {
                    throw new RuntimeException("Ocorreu um erro inesperado durante a injeção: " + e.getMessage(), e);
                }
            }
        }
    }
}
