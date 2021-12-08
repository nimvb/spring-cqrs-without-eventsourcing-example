package com.nimvb.app.purecqrswithaxon.repository;

import com.nimvb.app.purecqrswithaxon.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryQueryRepository extends JpaRepository<Category,String> {
}
