package br.com.guilhermeroliveira.alura.sevendaysofcode;

import br.com.guilhermeroliveira.alura.sevendaysofcode.service.IMDbHttpService;

public class App {
    public static void main(String[] args) {
        System.out.println(IMDbHttpService.getTop250Movies());
    }
}
