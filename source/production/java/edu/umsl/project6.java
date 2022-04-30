package edu.umsl;
import org.jsoup.Jsoup;
import java.util.HashMap;
import java.util.HashSet;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class project6 {
    private static final int MAX_PAGES = 1000;//declare max number of pages to visit
    private HashSet<String> titles = new HashSet<>();//collect  tittles
    private HashSet<String> urlVisited = new HashSet<>();//keep track of urls
    private HashMap<String, Integer> map = new HashMap<>();//keep track of words

    //function to crawl web
    public void getLinks(String startURL) {
        if ((titles.size() < MAX_PAGES) && !urlVisited.contains(startURL)) {
            urlVisited.add(startURL);
            try {
                Document doc = Jsoup.connect(startURL).get();
                Elements linksFromPage = doc.select("a[href]");
                String title = doc.select("title").first().text();
                titles.add(title);
                String text = doc.body().text();
                for (Element link : linksFromPage) {
                    if (titles.size() <= MAX_PAGES) {
                        Thread.sleep(1000);//sleep for a second
                        getLinks(link.attr("abs:href"));
                    } else {
                        System.out.println("URL couldnt visit");
                        System.out.println(startURL + ", " + urlVisited.size());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    //method to print all titles
    public void PrintAllTitles() {

        for (String t : titles) {
            System.out.println(t);
        }
    }

    //method to print word and count
    public void PrintAllWordsAndCount() {

        for (String key : map.keySet()) {

            System.out.println(key + " : " + map.get(key));
        }
    }

    private void CountWords(String text) {

        String[] lines = text.split(" ");

        for (String word : lines) {

            if (map.containsKey(word)) {
                int val = map.get(word);
                val += 1;
                map.remove(word);
                map.put(word, val);
            } else {
                map.put(word, 1);
            }

        }
    }

    public static class driver {

        public static void main(String[] args) {

            project6 c = new project6();
            c.getLinks("https://en.wikipedia.org/wiki/Science");
            System.out.println("*******************************Printing all titles*******************************");
            c.PrintAllTitles();
            System.out.println("*******************************Printing all Words*******************************");
            c.PrintAllWordsAndCount();
        }

    }
}


