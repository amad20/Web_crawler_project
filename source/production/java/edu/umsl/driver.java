package edu.umsl;

public class driver {

        public static void main(String[] args) {

            project6 c = new project6();
            c.getLinks("https://en.wikipedia.org/wiki/Science");
            System.out.println("*******************************Printing all titles*******************************");
            c.PrintAllTitles();
            System.out.println("*******************************Printing all Words*******************************");
            c.PrintAllWordsAndCount();
        }
}
