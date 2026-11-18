package com.crowdsafety.repo;
import com.crowdsafety.model.PredictionRecord; import org.springframework.data.jpa.repository.JpaRepository;
public interface PredictionRepository extends JpaRepository<PredictionRecord,Long>{}
