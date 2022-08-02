package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class JSONParserTest {
	@Test
	public void shouldParseAJSONObject() {
		String json = "{\"id\":\"tt5491994\",\"rank\":\"1\"}";

		Map<String, String> map = JSONParser.parseObject(json);

		assertEquals(map.get("id"), "tt5491994");
		assertEquals(map.get("rank"), "1");
	}

	@Test
	public void shouldCorrectlyParseAJSONArray() {
		String json = "[{\"id\":\"tt5491994\",\"rank\":\"1\"},{\"id\":\"tt0903747\",\"rank\":\"2\"}]";

		List<Map<String, String>> list = JSONParser.parseArray(json);

		assertEquals(list.get(0).get("id"), "tt5491994");
		assertEquals(list.get(0).get("rank"), "1");

		assertEquals(list.get(1).get("id"), "tt0903747");
		assertEquals(list.get(1).get("rank"), "2");
	}
}
