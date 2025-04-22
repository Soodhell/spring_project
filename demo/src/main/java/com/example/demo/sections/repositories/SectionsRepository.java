package com.example.demo.sections.repositories;

import com.example.demo.sections.models.Sections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionsRepository extends JpaRepository<Sections, Long> {
}
