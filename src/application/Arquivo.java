package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Produto;

public class Arquivo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Deseja cadastrar produto (S/N)? ");
		char insereProduto = sc.next().toUpperCase().charAt(0);

		List<Produto> listaProdutos = new ArrayList<>();

		System.out.print("Informe o diretório do arquivo:");
		String diretorio = sc.next();
		System.out.print("Informe o nome do arquivo com extensão: ");
		String arquivo = sc.next();

		while (insereProduto == 'S' || insereProduto == 's') {
			System.out.print("Informe o produto: ");
			String produto = sc.next();
			System.out.print("Quantidade: ");
			Long quantidade = sc.nextLong();
			System.out.print("Valor: ");
			Double valor = sc.nextDouble();

			listaProdutos.add(new Produto(produto, quantidade, valor));

			System.out.println("Deseja incluir mais produtos (S/N): ");
			insereProduto = sc.next().toUpperCase().charAt(0);
		}

		if (!listaProdutos.isEmpty()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(diretorio + arquivo, true))) {
				for (Produto produto : listaProdutos) {
					bw.write(produto.toString());
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			boolean arquivoCriado = new File(diretorio + "\\subDiretorio\\").mkdir();
			if (arquivoCriado) {
				List<Produto> listaProdutos2 = new ArrayList<>();

				try (BufferedReader br = new BufferedReader(new FileReader(diretorio + arquivo))) {
					String linha = br.readLine();
					while (linha != null) {
						String[] linhaProduto = linha.split(",");

						listaProdutos2.add(new Produto(linhaProduto[0], Long.parseLong(linhaProduto[1]),
								Double.parseDouble(linhaProduto[2])));

						linha = br.readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(diretorio + "\\subDiretorio\\" + arquivo))) {
					for (Produto produto : listaProdutos2) {
						bw.write(produto.getProduto() + ", " + produto.valorTotal());
						bw.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		sc.close();

	}

}
