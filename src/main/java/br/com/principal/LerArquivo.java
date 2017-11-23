package br.com.principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo {

	public String LerArquivoDeEsportes() {
		StringBuffer texto = new StringBuffer();
		try {
			BufferedReader info = new BufferedReader(new FileReader("texto/teste.txt"));
			// TODO: Tratar o arquivo de texto antes de retornar!
			String linha = info.readLine();
			while (linha != null) {
				texto.append(linha);
				linha = info.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de esportes");
		}
		return texto.toString();
	}

}
