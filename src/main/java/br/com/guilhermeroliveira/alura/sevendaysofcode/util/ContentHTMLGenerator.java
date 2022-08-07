package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.content.Content;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class ContentHTMLGenerator {

	private static final String HEAD_WITH_BOOTSTRAP = """
			<head>
				<meta charset=\"utf-8\">
				<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
				<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
					"integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
			</head>
			""";

	private static final String DIV_TEMPLATE = """
			<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
				<h4 class=\"card-header\">%s</h4>
				<div class=\"card-body\">
					<img class=\"card-img\" src=\"%s\" alt=\"%s\">
					<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
				</div>
			</div>
			""";

	public static String writeContents(List<Content> contents) {
		var sw = new StringWriter();
		var pw = new PrintWriter(sw);
		initializeHTML(pw);

		contents.stream().forEach(content -> writeContentDiv(content, pw));

		closeHTML(pw);

		return sw.toString();
	}

	private static void writeContentDiv(Content content, PrintWriter writer) {
		writer.println(String.format(DIV_TEMPLATE, content.getTitle(), content.getImageUrl(), content.getTitle(),
				content.getRating(), content.getYear()));
	}

	private static void initializeHTML(PrintWriter writer) {
		writer.println("<html>");
		writer.println(HEAD_WITH_BOOTSTRAP);
		writer.println("<body>");
		writer.println("<div class=\"card-columns\" style=\"margin-bottom:10px;\">");
	}

	private static void closeHTML(PrintWriter writer) {
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
