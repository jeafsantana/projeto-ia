package br.com.principal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LerArquivo {
	
	private class Frequencia {
		
		public int valor;
		public String palavra;
		
		public Frequencia(String palavra, int valor) {
			
			this.palavra = palavra;
			this.valor = valor;
			
		}
		
		public String setPalavra(String p) {
			
			palavra = p;
			
			return palavra;
			
		}
		
		public int setValor(int v) {
			
			valor = v;
			
			return valor;		
			
		}
		
		
		public String getPalavra() {
			
			return palavra;
			
		}
		
		public int getValor() {
			
			return valor;		
			
		}
		
		public int incrementaValor() {
			
			valor++;
			
			return valor;		
			
		}
		
		
		
	}
	
	ArrayList<ArrayList<String>> texto = new ArrayList<ArrayList<String>>();
	ArrayList<Frequencia> bow = new ArrayList<Frequencia> ();

	public String LerArquivoDeEsportes() {
		StringBuffer textoo = new StringBuffer();
		try {
			BufferedReader info = new BufferedReader(new FileReader("texto/esportes/texto1-esportes.txt"));
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter("texto/vyg.txt"));
			ArrayList<String> lista = new ArrayList<String>();
			// TODO: Tratar o arquivo de texto antes de retornar!
			String linha = info.readLine();
			String[] palavra;
			while (linha != null) {
				linha = tratarTexto(linha);
				textoo.append(linha);
				buffWrite.append(linha);
				buffWrite.append("\n");
				lista = ngrama(2,linha);
				linha = info.readLine();
			//	System.out.println(lista);
				
			}
			buffWrite.close();

		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de esportes");
		}
		
		contaFrequencia(texto);
		ordenaBOW(bow);
		
      /*		for(int i = 0; i < bow.size(); i++) {
			
			System.out.println( " lista ordenada: " + bow.get(i).palavra + " " + bow.get(i).valor);
			
		}*/
		
		

		return texto.toString();
	}
	
	public String[] sinalizaVazios(String[] aux) {
		
		for(int i = 0; i < aux.length; i++) {
			
			if(aux[i].equalsIgnoreCase("") ) {
				
				aux[i] = "*";
			}
		    
		
			
		}
		
		
		return aux;
	}
	
	public ArrayList ngrama(int n, String linha) {
		
		ArrayList<String> lista = new ArrayList<String>();
		
		String[] palavras = linha.split(" ");
		palavras = sinalizaVazios(palavras);
		
		
		switch (n) {
			
			case 1:
				int cont = 0;
				for(int i = 0; i < palavras.length; i++) {
					
					if(palavras[i] != "*") {
						lista.add(palavras[i]);						
					}
					
					
				}
				texto.add(lista);
				break;
				
			case 2:
				
			for(int i = 0; i < palavras.length; i++) {
					int j = i + 1;
					if(palavras[i] != "*") {
						
						if (j < palavras.length) {
							
							if(palavras[j] == "*") {while(palavras[j] == "*") {int w = j; w++; j = w;}}
							
							if(palavras[j] != "*") {

							lista.add(palavras[i] + " " +  palavras[j]);
							
							}
						}						
					}
					
					
				}
			texto.add(lista);
			
				break;
			case 3:
				
				for(int i = 0; i < palavras.length; i++) {
					int j = i + 1;
					int k = j + 1;
					if(palavras[i] != "*") {
						
						if (j < palavras.length && k < palavras.length - 1) {
							
							if(palavras[j] == "*") {while(palavras[j] == "*") {int w = j; w++; j = w;}}
							if(palavras[k] == "*") {while(palavras[k] == "*") {int w = k; w++; k = w;}}
							
							
							if(palavras[j] != "*" && palavras[k] != "*") {

							lista.add(palavras[i] + " " +  palavras[j] + " " + palavras[k]);
							
							}
						}						
					}
					
					
				}
				
				texto.add(lista);
				
				
				break;
			default:
				
			System.out.println("Valor Inválido");
			
		}
		
		return lista;
	}
	
	public void contaFrequencia(ArrayList<ArrayList<String>> texto){
		
		boolean contido = false;
		Frequencia nova;
		
		for(int i = 0; i < texto.size(); i++) { // percorrendo as linhas do texto
			
			for(int j = 0; j < texto.get(i).size(); j++) { //percorrendo as palavras das linhas
				
				for(int k = 0; k < bow.size(); k++) { // lista de frequencias
					
					
					if(bow.get(k).getPalavra().equalsIgnoreCase(texto.get(i).get(j))) {contido = true;}					
		
					if(contido) {bow.get(k).incrementaValor();} 
					
					
				}
				
				
				if(!contido) {
					

					nova = new Frequencia(texto.get(i).get(j),1) ;					
					bow.add(nova);
					
				}
				contido = false;
				
				
			}
			
			
		}
			
				
	}
	
	public void ordenaBOW(ArrayList<Frequencia> lista) {
		
		int i = 0;
		int j = 0;
		boolean ok = true;
		
		while (true) {
			
			j = i + 1;
			
			if(j < lista.size() - 1) {				
				
				if(lista.get(i).valor < lista.get(j).valor) {
										
					Frequencia aux1 = lista.get(i);
					
					lista.set(i, lista.get(j));				
					lista.set(j, aux1);
					
					ok = false;
					
				}
			}
		
			i++;
			
			if(i > lista.size()  && ok) {	
				
				break;
				
			} else {
				if( i > lista.size() && !ok) {
										
					i = 0;
					ok = true;
				}
			}			
			
		}
		
	}
	
	public String tratarTexto(String linha) {

		
		linha=linha.replace("<head>", "");
		linha=linha.replace("<edic>", "");
		linha=linha.replace("</edic>", "");
		linha=linha.replace("<autor>", "");
		linha=linha.replace("</autor>", "");
		linha=linha.replace("</head>", "");
		linha=linha.replace("<body>", "");
		linha=linha.replace("<subtítulo>", "");
		linha=linha.replace("</subtítulo>", "");
		linha=linha.replace("<título>", "");
		linha=linha.replace("</título>", "");
		linha=linha.replace("</body>", "");
		linha=linha.replace("<", "");
		linha=linha.replace("|", "");
		linha=linha.replace("/", " ");
		linha=linha.replace(",", " ");
		
		
		
		return linha;

	}
	
}
