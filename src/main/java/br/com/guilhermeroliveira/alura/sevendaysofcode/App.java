package br.com.guilhermeroliveira.alura.sevendaysofcode;

import br.com.guilhermeroliveira.alura.sevendaysofcode.dto.IMDbTop250MoviesResponseDto;
import br.com.guilhermeroliveira.alura.sevendaysofcode.service.IMDbHttpService;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.JSONParser;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        String json = IMDbHttpService.getTop250Movies();

        // List<Map<String, String>> movies = manualParsing(json);

        // movies.stream().map(movie -> movie.get("title")).forEach(System.out::println);
        // movies.stream().map(movie -> movie.get("image")).forEach(System.out::println);

        IMDbTop250MoviesResponseDto response = gsonParsing(json);

        response.getItems().stream().map(movie -> movie.getTitle()).forEach(System.out::println);
        response.getItems().stream().map(movie -> movie.getImage()).forEach(System.out::println);
    }

    private static List<Map<String, String>> manualParsing(String json) {
        String array = json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);

        List<Map<String, String>> movies = JSONParser.parseArray(array);
        return movies;
    }

    private static IMDbTop250MoviesResponseDto gsonParsing(String json) {
        Gson gson = new Gson();

        IMDbTop250MoviesResponseDto response = gson.fromJson(json, IMDbTop250MoviesResponseDto.class);

        return response;
    }
}
