package main;

import controllers.CadastroLojaController;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testes de cadastramento:");
		//Cadastro da loja
		CadastroLojaController loja1 = new CadastroLojaController("Limpeza", "xxxxxx", "47984985935");
		
		//Cadastro do fornecedor
		System.out.println(loja1.adicionarFornecedor("Arco Dourado", "xxxxxx", "47984985935"));
		//Remover fornecedor
		System.out.println(loja1.removerFornecedor("Arco Dourado"));
		System.out.println(loja1.removerFornecedor("Arco Dourado"));
		//
		//Teste duplicado
		System.out.println(loja1.adicionarFornecedor("Arco Dourado", "xxxxx", "47984985935"));
		System.out.println(loja1.adicionarFornecedor("Arco Dourado", "xxxxx", "47984985935"));
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println();
		
		
		System.out.println("Testes de entrada e saída do estoque");
		//Adicionando itens
		System.out.println(loja1.adicionarProduto("Desinfetante", 25, 19.99));
		System.out.println(loja1.adicionarProduto("Detergente", 5, 1.99));
		System.out.println(loja1.adicionarProduto("Detergente", 5, 1.99));
		
		//Removendo itens
		//caso 1: estoque > remoção
		System.out.println("caso  1 " + loja1.removerProduto("Detergente", 2));
		
		//caso 2: estoque < remoção
		System.out.println("caso  2 " + loja1.removerProduto("Detergente", 10));
		
		//caso 3: estoque == remoção
		System.out.println("caso  3 " + loja1.removerProduto("Detergente", 8));
		
		//caso 4: produto não encontrado
		System.out.println("caso  4 " + loja1.removerProduto("Pasta de dente", 8));
		
		
		System.out.println(loja1.obterHistoricoFormatado());
	}

}

