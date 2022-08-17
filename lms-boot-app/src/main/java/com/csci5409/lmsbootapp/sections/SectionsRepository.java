package com.csci5409.lmsbootapp.sections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsRepository extends JpaRepository<SectionsModel, Long> {
}
