package br.com.guilhermeroliveira.alura.sevendaysofcode.model.content;

import br.com.guilhermeroliveira.alura.sevendaysofcode.util.mappers.ContentMapper;
import br.com.guilhermeroliveira.alura.sevendaysofcode.util.mappers.MovieMapper;

public enum ContentSource {
	IMDB(new MovieMapper());

	private final ContentMapper mapper;

	public ContentMapper getMapper() {
		return mapper;
	}

	private ContentSource(ContentMapper mapper) {
		this.mapper = mapper;
	}
}
