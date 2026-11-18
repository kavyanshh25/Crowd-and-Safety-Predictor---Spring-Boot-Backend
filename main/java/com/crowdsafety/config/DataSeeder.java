package com.crowdsafety.config;
import com.crowdsafety.model.*; import com.crowdsafety.repo.*; import com.fasterxml.jackson.databind.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.core.io.ClassPathResource; import java.util.*;
@Configuration public class DataSeeder {
 @Bean CommandLineRunner seed(PlaceRepository places,CrowdObservationRepository observations,ObjectMapper mapper){return args->{
  if(places.count()>0)return;
  JsonNode array=mapper.readTree(new ClassPathResource("places.json").getInputStream());
  for(JsonNode p:array){String id=p.path("id").asText(); PlaceEntity entity=places.save(new PlaceEntity(id,p.path("name").asText(),p.path("category").asText(),p.path("city").asText(),mapper.writeValueAsString(p)));
   for(int h=0;h<24;h++){observations.save(new CrowdObservation(entity,"WEEKDAY",h,p.path("hourlyWeekday").get(h).asInt(),"SAMPLE_DATA")); observations.save(new CrowdObservation(entity,"WEEKEND",h,p.path("hourlyWeekend").get(h).asInt(),"SAMPLE_DATA"));}
  }
  System.out.println("Seeded sample places and hourly observations. These are illustrative prototype data.");
 };}
}
