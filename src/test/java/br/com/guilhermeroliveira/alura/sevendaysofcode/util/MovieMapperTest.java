package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.mappers.MovieMapper;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class MovieMapperTest {
	@Test
	public void shouldParseAJSONObject() {
		String json = "{\"title\":\"tt5491994\",\"imDbRating\":\"1\"}";

		Map<String, String> map = JSONParser.parseObject(json);

		MovieMapper mapper = new MovieMapper();
		Movie movie = mapper.parseObject(map);

		assertEquals("tt5491994", movie.getTitle());
		assertEquals("1", movie.getRating());
	}

	@Test
	public void shouldCorrectlyParseAJSONArray() {
		String json = "[{\"title\":\"tt5491994\",\"imDbRating\":\"1\"},{\"title\":\"tt0903747\",\"imDbRating\":\"2\"}]";

		List<Map<String, String>> list = JSONParser.parseArray(json);
		MovieMapper mapper = new MovieMapper();
		List<Movie> contents = mapper.parseArray(list);

		assertEquals("tt5491994", contents.get(0).getTitle());
		assertEquals("1", contents.get(0).getRating());

		assertEquals("tt0903747", contents.get(1).getTitle());
		assertEquals("2", contents.get(1).getRating());
	}
}
