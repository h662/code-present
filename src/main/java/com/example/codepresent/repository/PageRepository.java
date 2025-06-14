package com.example.codepresent.repository;

import com.example.codepresent.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
}
