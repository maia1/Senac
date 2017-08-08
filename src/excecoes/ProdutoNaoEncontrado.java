package excecoes;

public class ProdutoNaoEncontrado extends Exception{
    public ProdutoNaoEncontrado(){
        super("Produto não encontrado!!");
    }
}