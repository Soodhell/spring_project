package com.example.demo.representationObjects.news;

import com.example.demo.model.news.News;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class RepresentationNews {

    public static Map<Long, PerformanceNews> selectAll(Map<Long, News> news) {

        Map<Long, PerformanceNews> result = new HashMap<>();

        for (News newsItem : news.values()) {
            result.put(newsItem.getId(), new PerformanceNews(
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
                news.getTitle(),
                news.getContent(),
                news.getAuthor().getMail(),
                news.getNameImg()
        );
    }

}
