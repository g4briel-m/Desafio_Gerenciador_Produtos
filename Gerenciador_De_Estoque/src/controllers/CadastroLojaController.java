package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.CadastroModel;
import models.ListaEstoqueModel;

public class CadastroLojaController extends CadastroModel {
    
    private List<CadastroFornecedorController> fornecedor;
	private List<ListaEstoqueModel> estoque;
	private HistoricoEstoqueController historicoEstoque;

	public CadastroLojaController(String nome, String cnpj, String telefone) {
		super(nome, cnpj, telefone);
		this.fornecedor = new ArrayList<>();
		this.estoque = new ArrayList<>();
		this.historicoEstoque = new HistoricoEstoqueController();
	}

	public List<CadastroFornecedorController> getFornecedor() {
		return fornecedor;
	}

	public List<ListaEstoqueModel> getEstoque() {
		return estoque;
	}
	
	public String adicionarFornecedor(String nome, String cnpj, String telefone) {
		Iterator<CadastroFornecedorController> iterator = this.fornecedor.iterator();
		
		while(iterator.hasNext()) {
			CadastroFornecedorController fornecedores = iterator.next();
			if (fornecedores.getNome().equals(nome)) {
				return "Fornecedor já cadastrado";
			}
		}
		
		this.fornecedor.add(new CadastroFornecedorController(nome, cnpj, telefone));
		return "Fornecedor cadastrado com sucesso";
	}
	
	public String removerFornecedor(String nome) {
		Iterator<CadastroFornecedorController> iterator = this.fornecedor.iterator();
		
		while(iterator.hasNext()) {
			CadastroFornecedorController fornecedor = iterator.next();
			if (fornecedor.getNome().equals(nome)) {
				iterator.remove();
				return "Fornecedor Removido com Sucesso";
			}
		}
		return "Fornecedor não cadastrado";
	}

	public String adicionarProduto(String nome, Integer quantidade, Double valor) {
		Iterator<ListaEstoqueModel> iterator = this.estoque.iterator();

		while (iterator.hasNext()) {
			ListaEstoqueModel produto = iterator.next();
			if (produto.getNome().equals(nome)) {
				// Se o produto já existe, atualiza a quantidade e retorna
				produto.setQuantidade(quantidade + produto.getQuantidade());
				historicoEstoque.adicionarRegistro("Produto adicionado: " + nome + ", Quantidade atualizada: " + produto.getQuantidade());
				return "Produto já cadastrado, quantidade atualizada.";
			}
		}

		// Se o produto não foi encontrado, adiciona um novo produto na lista
		this.estoque.add(new ListaEstoqueModel(nome, quantidade, valor));
		historicoEstoque.adicionarRegistro("Produto adicionado: " + nome + ", Quantidade: " + quantidade);
		return "Produto adicionado com sucesso";
	}

	public String removerProduto(String nome, Integer quantidade) {
		// Itera pela lista usando um iterador para evitar
		// ConcurrentModificationException
		Iterator<ListaEstoqueModel> iterator = this.estoque.iterator();

		while (iterator.hasNext()) {
			ListaEstoqueModel produto = iterator.next(); // referência do objeto da iteração atual que é o mesmo de estoque
			// produto recebe e passa a ter o endereço de memória do mesmo objeto da lista
			// estoque, refletindo assim as alterações

			if (produto.getNome().equals(nome)) {
				// Verifica se a quantidade a ser removida é menor ou igual à quantidade em
				// estoque
				if (quantidade <= produto.getQuantidade()) {
					produto.setQuantidade(produto.getQuantidade() - quantidade);
					historicoEstoque.adicionarRegistro("Produto removido: " + nome + ", Quantidade: " + quantidade  + ", Quantidade atualizada: " + produto.getQuantidade());

					// Se a quantidade restante for zero, remove o produto da lista
					if (produto.getQuantidade() == 0) {
						iterator.remove();
						historicoEstoque.adicionarRegistro("Produto removido e o estoque zerado!: " + nome);
						return "Produto removido e o estoque foi zerado!";
					}

					return "Removido com sucesso";
				} else {
					return "Quantidade a ser removida maior que a quantidade em estoque";
				}
			}
		}

		return "Produto não encontrado no estoque";
	}

	public String obterHistoricoFormatado() {
	    StringBuilder formatoSimples = new StringBuilder();

	    for (String registro : historicoEstoque.getHistorico()) {
	        formatoSimples.append(registro).append("\n");
	    }

	    return formatoSimples.toString();
	}
}
