package com.example.demo.representationObjects.sections;

import com.example.demo.sections.models.Sections;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class RepresentationSections {
    public static PerformanceSections getPerformanceSection(Sections sections) {
        return new PerformanceSections(
                sections.getId(),
                sections.getTitle(),
                sections.getContent(),
                sections.getNameImg(),
                sections.getTeacher().getMail()
        );
    }

    public static List<PerformanceSections> getPerformanceSections(Map<Long, Sections> sectionsMap) {
        List<PerformanceSections> performanceSectionsList = new ArrayList<>();

        for (Sections sections : sectionsMap.values()) {
            performanceSectionsList.add(
                new PerformanceSections(
                        sections.getId(),
                        sections.getTitle(),
                        sections.getContent(),
                        sections.getNameImg(),
                        sections.getTeacher().getMail()
                )
            );
        }

        return performanceSectionsList;
    }
}
