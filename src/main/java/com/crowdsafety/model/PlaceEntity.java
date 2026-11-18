package com.crowdsafety.model;
import jakarta.persistence.*;
@Entity @Table(name="places")
public class PlaceEntity {
 @Id @Column(length=100) private String id;
 @Column(nullable=false) private String name;
 private String category; private String city;
 @Lob @Column(name="place_json",columnDefinition="LONGTEXT") private String placeJson;
 protected PlaceEntity(){}
 public PlaceEntity(String id,String name,String category,String city,String json){this.id=id;this.name=name;this.category=category;this.city=city;this.placeJson=json;}
 public String getId(){return id;} public String getName(){return name;} public String getCategory(){return category;} public String getCity(){return city;} public String getPlaceJson(){return placeJson;}
}
