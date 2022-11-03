package com.smartTech.RestApi.Service;

import com.smartTech.RestApi.Model.Services;

import java.util.List;

public interface ServicesServices {


    List<Services> getServices();
    Services saveServices(Services services);

    Services getSingleService(Long id);

    void deleteService(Long id );

    Services updateService(Services services );
    List <Services> getServicesTitle(String title);

    List<Services> getServicesByTitleAndLocation(String address);

    List <Services>getServicesByPrice(int price );


    List <Services>getServicesBySlug(String slug);


    List <Services>getServicesByDescription(String description);

    List <Services>getServicesByCategory(int category_id);


    List <Services>getServicesByRegion_id(String region_id);

    void deleteService(String description);



    List<Services> findViewsWithSortingAsc();

}
