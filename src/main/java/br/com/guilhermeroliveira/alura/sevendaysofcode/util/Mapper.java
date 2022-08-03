package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Mapper {

	public static <T> List<T> parseArray(List<Map<String, String>> list, Class<T> clazz) {
		List<T> parsedList = new ArrayList<>();
		for (var object : list) {
			T parsedObject = parseObject(object, clazz);
			if (parsedObject != null)
				parsedList.add(parsedObject);
		}

		return parsedList;
	}

	public static <T> T parseObject(Map<String, String> object, Class<T> clazz) {
		try {
			T parsedObject = clazz.getConstructor().newInstance();
			for (Field f : clazz.getDeclaredFields()) {
				final String fieldName = f.getName();

				if (f.getType().isPrimitive() && object.get(fieldName) == null)
					continue;

				Method setter = Stream.of(clazz.getDeclaredMethods())
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
