package com.ohgiraffers.jpaproject.living.repository;

import com.ohgiraffers.jpaproject.living.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /* JPQL */
    //@Query("SELECT c FROM Category c order by c.categoryNo")

        /* Native Query*/
    @Query(
            value = "SELECT category_no, category_name, room_code FROM tbl_space order by category_no",
            nativeQuery = true)
    List<Category> findAllCategory();
}
