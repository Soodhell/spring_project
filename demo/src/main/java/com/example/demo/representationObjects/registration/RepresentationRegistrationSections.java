package com.example.demo.representationObjects.registration;

import com.example.demo.registration.models.RegistrationSection;

import java.util.HashMap;
import java.util.Map;

public class RepresentationRegistrationSections {
    public static Map<Long, PerformanceRegistrationSections> selectAll(Map<Long, RegistrationSection> registrationSections){

        Map<Long, PerformanceRegistrationSections> result = new HashMap<>();
        for(Map.Entry<Long, RegistrationSection> entry : registrationSections.entrySet()){
            result.put(entry.getKey(), new PerformanceRegistrationSections(
                    entry.getValue().getSection().getTitle(),
                    entry.getValue().getSection().getNameImg()
            ));
        }
        return result;
    }
}
