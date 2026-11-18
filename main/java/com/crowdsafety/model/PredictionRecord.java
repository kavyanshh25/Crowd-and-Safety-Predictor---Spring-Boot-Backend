package com.crowdsafety.model;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="prediction_records")
public class PredictionRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="place_id",nullable=false) private String placeId;
 private LocalDate requestedDate; private LocalTime requestedTime; private String crowdLevel; private String riskLevel; private int crowdIndex; private LocalDateTime createdAt;
 protected PredictionRecord(){}
 public PredictionRecord(String p,LocalDate d,LocalTime t,String c,String r,int i){placeId=p;requestedDate=d;requestedTime=t;crowdLevel=c;riskLevel=r;crowdIndex=i;createdAt=LocalDateTime.now();}
}
