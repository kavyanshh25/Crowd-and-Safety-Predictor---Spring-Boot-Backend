package com.crowdsafety.repo;
import com.crowdsafety.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface CrowdObservationRepository extends JpaRepository<CrowdObservation,Long>{ List<CrowdObservation> findByPlace_IdAndDayType(String id,String dayType); }
