package br.com.guilhermeroliveira.alura.sevendaysofcode.util.mappers;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MovieMapper implements ContentMapper {

	@Override
	public List<Movie> parseArray(List<Map<String, String>> list) {
		List<Movie> parsedList = new ArrayList<>();
		for (var object : list) {
			Movie parsedObject = parseObject(object);
			if (parsedObject != null)
				parsedList.add(parsedObject);
		}

		return parsedList;
	}

	@Override
	public Movie parseObject(Map<String, String> object) {
		try {
			Movie parsedObject = new Movie();
			for (Field f : Movie.class.getDeclaredFields()) {
				final String fieldName = f.getName();

				if (f.getType().isPrimitive() && object.get(fieldName) == null)
					continue;

				Method setter = Stream.of(Movie.class.getDeclaredMethods())
						.filter(m -> m.getName().equalsIgnoreCase("set" + fieldName)).findFirst().orElse(null);

				if (setter != null)
					setter.invoke(parsedObject, object.get(fieldName));
			}

			return parsedObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
