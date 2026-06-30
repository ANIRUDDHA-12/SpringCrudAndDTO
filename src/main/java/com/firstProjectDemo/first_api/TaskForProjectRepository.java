package com.firstProjectDemo.first_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskForProjectRepository extends JpaRepository<TaskForProject,Integer> {
}
