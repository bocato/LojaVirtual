package Loja;

/**
 * Created by gap on 02/06/15.
 */
public class Produto {

    private Integer id;
    private String nome;
    private Float preco;

    public Produto(Integer id, String nome, Float preco) {
         this.id = id;
         this.nome = nome;
         this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

}
