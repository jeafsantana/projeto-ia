package br.com.principal;

import java.util.Locale;

import org.cogroo.analyzer.Analyzer;
import org.cogroo.analyzer.ComponentFactory;
import org.cogroo.text.Document;
import org.cogroo.text.Sentence;
import org.cogroo.text.Token;
import org.cogroo.text.impl.DocumentImpl;

public class Principal {
	public static void main(String[] args) {
		/* configurações de idioma */
		ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));
		Analyzer cogroo = factory.createPipe();

		/* processar texto */
		LerArquivo leitor = new LerArquivo();
		Document document = new DocumentImpl();
		/* processando texto sobre teste */
		document.setText(leitor.LerArquivoDeEsportes());
		cogroo.analyze(document);

		/* lista de sentenças */
		for (Sentence sentence : document.getSentences()) {
			for (Token token : sentence.getTokens()) {
				token.getLemmas(); // array com os possíveis lemas
				if (token.getLemmas().length != 0) {
					System.out.print(token.getLemmas()[0]);
					System.out.print(" " +token.getPOSTag());
					System.out.println();
				}
			}
		}
	}
}
