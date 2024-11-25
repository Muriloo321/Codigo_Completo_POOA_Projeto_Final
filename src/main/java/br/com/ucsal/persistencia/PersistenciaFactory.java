package br.com.ucsal.persistencia;

import br.com.ucsal.logicaAnotacoes.InstanciaUnica;

public class PersistenciaFactory {

    public static final int MEMORIA = 0;
    public static final int HSQL = 1;

    public static ProdutoRepository<?, ?> getProdutoRepository(int type) {
        switch (type) {
            case MEMORIA:
            	System.out.println("Fábrica: Criando repositório em memória.");
                return InstanciaUnica.carregarSingleton(MemoriaProdutoRepository.class);
            case HSQL:
            	System.out.println("Fábrica: Criando repositório HSQLDB.");
                return new HSQLProdutoRepository();
            default:
                throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}
