package com.example.demo.representationObjects.news;

import com.example.demo.news.models.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepresentationNews {

    public static List<PerformanceNews> selectAll(Map<Long, News> news) {

        List<PerformanceNews> result = new ArrayList<>();

        for (News newsItem : news.values()) {
            result.add(new PerformanceNews(
                    newsItem.getId(),
                    newsItem.getTitle(),
                    newsItem.getContent(),
                    newsItem.getAuthor().getMail(),
                    newsItem.getNameImg()
            ));
        }

        return result;
    };

    public static PerformanceNews getNews(News news) {
        return new PerformanceNews(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getAuthor().getMail(),
                news.getNameImg()
        );
    }

}
