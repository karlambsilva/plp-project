package loo1.plp.orientadaObjetos1.expressao.valor;

import java.io.Serializable;

/**
 *
 * Classe que representa um valor concreto
 * 
 */
public interface ValorConcreto extends Valor, Serializable {
    /**
     * Determina igualdade entre objetos do tipo <code>Valor</code>.
     * baseando-se  no conteudo armazenado em tais objetos.
     *
     * @param o Objeto com o qual eh feita comparacao.
     */
	public boolean equals(ValorConcreto valor);	
}
