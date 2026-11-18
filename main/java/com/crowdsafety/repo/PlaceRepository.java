package com.crowdsafety.repo;
import com.crowdsafety.model.PlaceEntity; import org.springframework.data.jpa.repository.JpaRepository;
public interface PlaceRepository extends JpaRepository<PlaceEntity,String>{}
