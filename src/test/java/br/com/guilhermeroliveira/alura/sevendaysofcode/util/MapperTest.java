package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class MapperTest {
	@Test
	public void shouldParseAJSONObject() {
		String json = "{\"id\":\"tt5491994\",\"rank\":\"1\"}";

		Map<String, String> map = JSONParser.parseObject(json);
		Movie movie = Mapper.parseObject(map, Movie.class);

		assertEquals(movie.getId(), "tt5491994");
		assertEquals(movie.getRank(), "1");
	}

	@Test
	public void shouldCorrectlyParseAJSONArray() {
		String json = "[{\"id\":\"tt5491994\",\"rank\":\"1\"},{\"id\":\"tt0903747\",\"rank\":\"2\"}]";

		List<Map<String, String>> list = JSONParser.parseArray(json);
		List<Movie> movies = Mapper.parseArray(list, Movie.class);

		assertEquals(movies.get(0).getId(), "tt5491994");
		assertEquals(movies.get(0).getRank(), "1");

		assertEquals(movies.get(1).getId(), "tt0903747");
		assertEquals(movies.get(1).getRank(), "2");
	}
}
