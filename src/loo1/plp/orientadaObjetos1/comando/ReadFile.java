package loo1.plp.orientadaObjetos1.comando;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import loo1.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.declaracao.classe.DecClasseSimples;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import loo1.plp.orientadaObjetos1.expressao.Expressao;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo1.plp.orientadaObjetos1.expressao.valor.Valor;
import loo1.plp.orientadaObjetos1.expressao.valor.ValorRef;
import loo1.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import loo1.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import loo1.plp.orientadaObjetos1.memoria.DefClasse;
import loo1.plp.orientadaObjetos1.memoria.Objeto;
import loo1.plp.orientadaObjetos1.util.Tipo;

/**
 * Representa um comando de leitura.
 */
public class ReadFile implements IO{
	/**
	 * O identificador ao qual ser� atribu�do o valor lido.
	 */
    private Id id;
	/**
	 * O tipo do identificador cujo novo valor ser� lido.
	 */
    private Tipo tipoId;
    
    private Expressao dir;

	/**
	 * Construtor.
	 * @param id O identificador ao qual ser� a atribu�do o valor lido.
	 */
    public ReadFile ( Id id, Expressao dir){
        this.id = id;
        this.dir = dir;
    }

    /**
     * L� da entrada padr�o.
     * @param ambiente o ambiente de execu��o.
     * @return o ambiente depois de modificado pela execu��o
     * do comando read.
     *
     */
    public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, EntradaInvalidaException {
        
    	try{

        	String path = this.dir.avaliar(ambiente).toString();
        	
			//Carrega o arquivo
			FileInputStream arquivoLeitura = new FileInputStream(path);

			//Classe responsavel por recuperar os objetos do arquivo
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
						
			int count = 0;
			
			List <Objeto> objetos = new ArrayList<>();
			
			while (count != -1){
				try{
					objetos.add((Objeto) objLeitura.readObject());
					count++;
				}catch (EOFException eofExc){
					count = -1;
				}
			}
			
			ValorRef proxRef = ambiente.getProxRef();
			
			ambiente.mapObjeto(proxRef, objetos.get(1));
			ambiente.changeValor(this.id, proxRef);
			
    	}catch(NullPointerException | ObjetoNaoDeclaradoException | ClasseNaoDeclaradaException | 
    			IOException | ClassNotFoundException | ObjetoJaDeclaradoException exc){
        	exc.printStackTrace( );
    	} 

//        ambiente.changeValor(id, ambiente.read(this.tipoId));
        return ambiente;
    }

    /**
     * Realiza a verificacao de tipos da entrada
     * @param ambiente o ambiente de compila��o.
     * @return <code>true</code> se a express�o da entrada est� bem tipada;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException {
//          Alteramos a implementa��o, pois em tempo de compila��o n�o se pode saber
//          o tipo da entrada que ser� lida.

//            Tipo tipo = ambiente.getTipoEntrada();
//            Tipo tipo2 = id.getTipo(ambiente);

        this.tipoId = id.getTipo(ambiente);
        return id.checaTipo(ambiente);

        //return id.getTipo(ambiente).equals(ambiente.getTipoEntrada());
    }

}
