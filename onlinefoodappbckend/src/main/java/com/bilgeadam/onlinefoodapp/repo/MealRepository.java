package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends CrudRepository<Meal, String> {
    List<Meal> findAll();
    void deleteById(String code);
    Optional<Meal> findById(String code);
    List<Meal> findAllByCampaignTrue();

    @Query(value = "SELECT M.CODE, M.EMP_ID, M.NAME, M.PRICE, M.PHOTO, M.DETAIL, M.CREATION_DATE, M.CAMPAIGN FROM CART C INNER JOIN MEAL M ON C.CODE = M.CODE WHERE CUSTOMER_ID =?1", nativeQuery = true)
    List<Meal> getAllCart(long id);
}
