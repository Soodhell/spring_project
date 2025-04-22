package com.example.demo.representationObjects.registrationInEvent;

import com.example.demo.news.models.News;
import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import com.example.demo.representationObjects.news.PerformanceNews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepresentationRegistration {

    public static List<PerformanceRegistration> getPerformanceRegistration(List<RegistrationInEventModel> list){

        List<PerformanceRegistration> performanceRegistrations = new ArrayList<>();

        for(RegistrationInEventModel registrationInEventModel : list){
            performanceRegistrations.add(new PerformanceRegistration(
                    registrationInEventModel.getId(),
                    registrationInEventModel.getEvent().getId(),
                    registrationInEventModel.getUser().getMail(),
                    registrationInEventModel.getEvent().getTitle(),
                    registrationInEventModel.getEvent().getContent()
            ));
        }

        return performanceRegistrations;

    }

}
