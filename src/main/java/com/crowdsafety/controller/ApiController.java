package com.crowdsafety.controller;
import com.crowdsafety.service.CrowdService; import com.fasterxml.jackson.databind.JsonNode; import com.fasterxml.jackson.databind.node.ObjectNode; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.validation.annotation.Validated; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") @Validated
public class ApiController {
 private final CrowdService service; public ApiController(CrowdService s){service=s;}
 @GetMapping("/places") public List<JsonNode> places(@RequestParam(defaultValue="") String search,@RequestParam(defaultValue="") String category,@RequestParam(defaultValue="") String city){return service.allPlaces(search,category,city);}
 @GetMapping("/places/{id}") public JsonNode place(@PathVariable String id){return service.place(id);}
 public record PredictionInput(@NotBlank String placeId,@NotBlank String date,@NotBlank String time){}
 @PostMapping("/predict") public ObjectNode predictOld(@Valid @RequestBody PredictionInput in){return service.predict(in.placeId(),in.date(),in.time());}
 @PostMapping("/predictions") public ObjectNode predict(@Valid @RequestBody PredictionInput in){return service.predict(in.placeId(),in.date(),in.time());}
 @GetMapping("/safety/notices") public List<JsonNode> notices(@RequestParam(defaultValue="") String placeId,@RequestParam(defaultValue="") String type,@RequestParam(defaultValue="") String severity){return service.notices(placeId,type,severity);}
 @GetMapping("/trends/heatmap/{id}") public ObjectNode heatmap(@PathVariable String id){return service.heatmap(id);}
 @GetMapping("/metadata/cities") public List<String> cities(){return service.cities();}
 @GetMapping("/metadata/categories") public List<String> categories(){return service.categories();}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","application","Crowd & Safety Predictor API");}
}
