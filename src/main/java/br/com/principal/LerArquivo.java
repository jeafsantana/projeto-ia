package br.com.principal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LerArquivo {

	public String LerArquivoDeEsportes() {
		StringBuffer texto = new StringBuffer();
		try {
			BufferedReader info = new BufferedReader(new FileReader("texto/texto1-esportes.txt"));
			// TODO: Tratar o arquivo de texto antes de retornar!
			String linha = info.readLine();
			String[] palavra;
			while (linha != null) {
				linha = tratarTexto(linha);
				texto.append(linha);
				linha = info.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de esportes");
		}
		return texto.toString();
	}
	
	public String tratarTexto(String linha) {
		
		linha=linha.replace("<head>", " ");
		linha=linha.replace("<edic>", " ");
		linha=linha.replace("</edic>", " ");
		linha=linha.replace("<autor>", " ");
		linha=linha.replace("</autor>", " ");
		linha=linha.replace("</head>", " ");
		linha=linha.replace("<body>", " ");
		linha=linha.replace("<subtítulo>", " ");
		linha=linha.replace("</subtítulo>", " ");
		linha=linha.replace("<título>", " ");
		linha=linha.replace("</título>", " ");
		linha=linha.replace("</body>", " ");
		linha=linha.replace("<", " ");
		linha=linha.replace("|", " ");
		linha=linha.replace("/", " ");
		
		
		
		return linha;

	}
	
}
