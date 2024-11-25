package com.example.demo.news.repositories;

import com.example.demo.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
}
