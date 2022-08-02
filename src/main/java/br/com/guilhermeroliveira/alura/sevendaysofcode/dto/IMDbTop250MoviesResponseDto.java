package br.com.guilhermeroliveira.alura.sevendaysofcode.dto;

import br.com.guilhermeroliveira.alura.sevendaysofcode.model.Movie;

import java.util.List;

public class IMDbTop250MoviesResponseDto {
	private List<Movie> items;

	private String errorMessage;

	public List<Movie> getItems() {
		return items;
	}

	public void setItems(List<Movie> items) {
		this.items = items;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
