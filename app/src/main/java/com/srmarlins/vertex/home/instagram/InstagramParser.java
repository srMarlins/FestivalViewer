package com.srmarlins.vertex.home.instagram;

import com.srmarlins.vertex.home.instagram.model.InstagramMedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaredFowler on 7/22/2016.
 */
public class InstagramParser {

    public InstagramParser() {

    }

    public List<InstagramMedia> parseHtmlForImageUrls(String html){
        List<InstagramMedia> urls = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements tagList = doc.select("div._jjzlb");
        for(Element element : tagList){
            Elements images = element.select("img");
            for(Element image : images) {
                urls.add(new InstagramMedia(image.absUrl("src"), image.attr("alt"), InstagramMedia.stringToType(element.text())));
            }
        }
        return urls;
    }
}
