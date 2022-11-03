package com.smartTech.RestApi.Repository;

import com.smartTech.RestApi.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services,Long> {

    List<Services> findBytitle(String title);
  //@Query("FROM Services WHERE address =: address ")

    List<Services> findByaddress(String address);

    List<Services>findByPrice(int price);

    List <Services>findBySlug(String slug);

    List<Services>findByDescription(String description);

     @Query("FROM  Services WHERE category_id=:category_id")
    List<Services>findByCategory_id(int category_id);

    @Query("FROM  Services WHERE region_id=:region_id")

    List<Services>findByRegion_id(String region_id);
  @Query("DELETE FROM Services WHERE description=:description")

    void DeleteByDescription(String description);

    @Query("FROM Services ORDER BY views ASC")
    List<Services> findAllOrderByPopulationAsc();
























}
