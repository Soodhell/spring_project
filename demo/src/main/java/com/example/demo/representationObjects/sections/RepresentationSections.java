package com.example.demo.representationObjects.sections;

import com.example.demo.sections.models.Sections;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class RepresentationSections {
    public static PerformanceSections getPerformanceSection(Sections sections) {
        return new PerformanceSections(
                sections.getTitle(),
                sections.getContent(),
                sections.getNameImg(),
                sections.getTeacher().getMail()
        );
    }

    public static Map<Long, PerformanceSections> getPerformanceSections(Map<Long, Sections> sectionsMap) {
        Map<Long, PerformanceSections> performanceSectionsMap = new HashMap<>();

        for (Sections sections : sectionsMap.values()) {
            performanceSectionsMap.put(
                    sections.getId(),
                    new PerformanceSections(
                            sections.getTitle(),
                            sections.getContent(),
                            sections.getNameImg(),
                            sections.getTeacher().getMail()
                    )
            );
        }

        return performanceSectionsMap;
    }
}
