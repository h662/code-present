package com.example.codepresent.repository;

import com.example.codepresent.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series,Long> {
}
