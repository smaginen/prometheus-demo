package com.smagin.prometheusdemo.data.repo;

import com.smagin.prometheusdemo.data.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
