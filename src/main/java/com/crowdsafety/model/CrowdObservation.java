package com.crowdsafety.model;
import jakarta.persistence.*;
@Entity @Table(name="crowd_observations", uniqueConstraints=@UniqueConstraint(columnNames={"place_id","day_type","hour_of_day"}))
public class CrowdObservation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="place_id",nullable=false) private PlaceEntity place;
 @Column(name="day_type",nullable=false) private String dayType;
 @Column(name="hour_of_day",nullable=false) private int hour;
 @Column(name="crowd_index",nullable=false) private int crowdIndex;
 @Column(name="data_source") private String dataSource;
 protected CrowdObservation(){}
 public CrowdObservation(PlaceEntity p,String d,int h,int i,String s){place=p;dayType=d;hour=h;crowdIndex=i;dataSource=s;}
 public int getHour(){return hour;} public int getCrowdIndex(){return crowdIndex;}
}
