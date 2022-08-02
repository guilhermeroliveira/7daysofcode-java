package br.com.guilhermeroliveira.alura.sevendaysofcode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {
	public static List<Map<String, String>> parseArray(String json) {
		List<Map<String, String>> list = new ArrayList<>();
		List<String> objects = Arrays.asList(json.substring(1, json.length() - 1).split("\\},\\{"));
		for (int i = 0; i < objects.size(); i++) {

			String object = objects.get(i);

			if (i < objects.size() - 1)
				object += "}";

			if (i > 0)
				object = "{" + object;

			list.add(parseObject(object));
		}
		return list;
	}

	public static Map<String, String> parseObject(String json) {
		Map<String, String> map = new HashMap<>();

		List<String> fields = Arrays.asList(json.substring(1, json.length() - 1).split("\",\""));
		for (int i = 0; i < fields.size(); i++) {
			String field = fields.get(i).trim();

			if (i == 0)
				field = field.substring(1);

			if (i == fields.size() - 1)
				field = field.substring(0, field.length() - 1);

			String key = field.split("\":\"")[0].trim();
			String value = field.split("\":\"")[1].trim();

			map.put(key, value);
		}

		return map;
	}
}
