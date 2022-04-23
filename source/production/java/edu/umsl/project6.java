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
                    if(titles.size() <= MAX_PAGES) {
                        Thread.sleep(1000);//sleep for a second
                        getLinks(link.attr("abs:href"));
                    }
                    else {
                        System.out.println("URL couldnt visit");
                        System.out.println(startURL + ", " + urlVisited.size());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
           catch (InterruptedException e) {

               e.printStackTrace();

           }
            catch (Exception e) {

                e.printStackTrace();
            }
        }

    }
}
