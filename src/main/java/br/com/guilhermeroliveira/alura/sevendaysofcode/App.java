package br.com.guilhermeroliveira.alura.sevendaysofcode;

import br.com.guilhermeroliveira.alura.sevendaysofcode.constants.SortDirection;
import br.com.guilhermeroliveira.alura.sevendaysofcode.model.content.Content;
import br.com.guilhermeroliveira.alura.sevendaysofcode.model.content.ContentSorter;
import br.com.guilhermeroliveira.alura.sevendaysofcode.model.content.ContentSource;
import br.com.guilhermeroliveira.alura.sevendaysofcode.service.http.IMDbHttpService;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.ContentHTMLGenerator;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.JSONParser;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import express.Express;
import express.utils.MediaType;
import express.utils.Status;

public class App {
    public static void main(String[] args) {
        Express server = new Express();
        server.get("/", (req, res) -> {
            res.setContentType(MediaType._html);

            ContentSource contentSource = null;
            ContentSorter contentSorter = null;
            SortDirection sortDirection = null;

            try {
                contentSource = ContentSource.valueOf(req.getQuery("type"));
                contentSorter = ContentSorter.valueOf(req.getQuery("sortBy"));
                sortDirection = SortDirection.valueOf(req.getQuery("direction"));
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
                res.sendStatus(Status._400);
                return;
            }

            String json = IMDbHttpService.getTop250Movies();
            List<Content> movies = manualParsing(json, contentSource);

            Comparator<Content> comparator = contentSorter.getComparator();
            if (sortDirection == SortDirection.DESC)
                comparator = comparator.reversed();

            movies.sort(comparator);

            String response = ContentHTMLGenerator.writeContents(movies);

            res.send(response);
        }).listen(() -> {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Listening at localhost:8080");
        }, 8080);
    }

    private static List<Content> manualParsing(String json, ContentSource source) {
        String array = json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);

        List<Map<String, String>> rawContents = JSONParser.parseArray(array);
        return source.getMapper().parseArray(rawContents).stream().map(c -> (Content) c).collect(Collectors.toList());
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
