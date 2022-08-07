package br.com.guilhermeroliveira.alura.sevendaysofcode.util.mappers;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.content.Content;

import java.util.List;
import java.util.Map;

public interface ContentMapper {
	public List<? extends Content> parseArray(List<Map<String, String>> list);

	public Content parseObject(Map<String, String> object);

}
