package br.com.guilhermeroliveira.alura.sevendaysofcode;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;
import br.com.guilhermeroliveira.alura.sevendaysofcode.service.IMDbHttpService;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.HTMLGenerator;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.JSONParser;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.Mapper;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import express.Express;
import express.utils.MediaType;

public class App {
    public static void main(String[] args) {
        Express server = new Express();
        server.get("/", (req, res) -> {
            res.setContentType(MediaType._html);

            String json = IMDbHttpService.getTop250Movies();
            List<Movie> movies = manualParsing(json);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            PrintWriter writer = new PrintWriter(os);
            HTMLGenerator.writeMovies(movies, writer);
            writer.close();

            String response = new String(os.toByteArray());
            res.send(response);
        }).listen(8080);
    }

    private static List<Movie> manualParsing(String json) {
        String array = json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);

        List<Map<String, String>> rawMovies = JSONParser.parseArray(array);
        return Mapper.parseArray(rawMovies, Movie.class);
    }

    // private static IMDbTop250MoviesResponseDto gsonParsing(String json) {
    //     Gson gson = new Gson();

    //     IMDbTop250MoviesResponseDto response = gson.fromJson(json, IMDbTop250MoviesResponseDto.class);

    //     return response;
    // }

    // private static void manualParseAndPrint(String json) {
    //     List<Movie> movies = manualParsing(json);

    //     movies.stream().map(movie -> movie.getTitle()).forEach(System.out::println);
    //     movies.stream().map(movie -> movie.getImage()).forEach(System.out::println);
    // }

    // private static void gsonParseAndPrint(String json) {
    //     IMDbTop250MoviesResponseDto response = gsonParsing(json);

    //     response.getItems().stream().map(movie -> movie.getTitle()).forEach(System.out::println);
    //     response.getItems().stream().map(movie -> movie.getImage()).forEach(System.out::println);
    // }
}
