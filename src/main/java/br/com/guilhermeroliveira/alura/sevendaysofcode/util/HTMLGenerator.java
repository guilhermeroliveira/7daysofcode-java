package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

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

	public static void writeMovies(List<Movie> movies, PrintWriter writer) {
		initializeHTML(writer);

		movies.stream().forEach(movie -> writeMovieDiv(movie, writer));

		closeHTML(writer);
	}

	private static void writeMovieDiv(Movie movie, PrintWriter writer) {
		writer.println(String.format(DIV_TEMPLATE, movie.getTitle(), movie.getImage(), movie.getTitle(),
				movie.getImdbRating(), movie.getYear()));
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
