package br.com.guilhermeroliveira.alura.sevendaysofcode.model.content;

import java.util.Comparator;

public enum ContentSorter {
	TITLE(Comparator.comparing(Content::getTitle)),
	RATING(Comparator.comparing(Content::getRating)),
	YEAR(Comparator.comparing(Content::getYear));

	private final Comparator<Content> comparator;

	public Comparator<Content> getComparator() {
		return comparator;
	}

	private ContentSorter(Comparator<Content> comparator) {
		this.comparator = comparator;
	}

}
