package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
				if (f.getType().isPrimitive() && object.get(f.getName()) == null)
					continue;

				final String fieldName = f.getName();
				Method setter = clazz
						.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),
								f.getType());

				setter.invoke(parsedObject, object.get(f.getName()));
			}

			return parsedObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
