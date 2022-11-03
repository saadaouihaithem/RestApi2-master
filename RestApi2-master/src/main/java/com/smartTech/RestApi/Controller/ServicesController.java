package com.smartTech.RestApi.Controller;

import com.smartTech.RestApi.Model.Services;
import com.smartTech.RestApi.Service.ServicesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServicesController {

    @Autowired
    private ServicesServices servicesServices;


    @GetMapping("/Services")
    public ResponseEntity<List<Services>> getServices() {
        return new ResponseEntity<List<Services>>(servicesServices.getServices(), HttpStatus.OK);
    }


    @GetMapping("/Services/{id}")
    public ResponseEntity<Services> getServices(@PathVariable Long id) {
        return new ResponseEntity<Services>(servicesServices.getSingleService(id), HttpStatus.OK);
    }


    @DeleteMapping("/Services")
    public void deleServices(@RequestParam Long id) {
        servicesServices.deleteService(id);
    }

    @PostMapping("/Services")
    public ResponseEntity<Services> SaveServices(@RequestBody Services services) {

        return new ResponseEntity<Services>(servicesServices.saveServices(services), HttpStatus.CREATED);
    }



    @GetMapping("/Services/filterBytitle")
    public ResponseEntity<List<Services>> getServicesBytitle(@RequestParam String title) {
        return new ResponseEntity<List<Services>>(servicesServices.getServicesTitle(title), HttpStatus.OK);
    }

    @GetMapping("/Services/filtreByTitleAndLocation")
    public ResponseEntity<List<Services>> getServicesByTitleAndLocation(@RequestParam String address) {
        return new ResponseEntity<List<Services>>(servicesServices.getServicesByTitleAndLocation(address), HttpStatus.OK);
    }

    @GetMapping("/Services/filterByPrice")

    public ResponseEntity<List<Services>> getServicesByPrice(@RequestParam int price) {

        return new ResponseEntity<List<Services>>(servicesServices.getServicesByPrice(price), HttpStatus.OK);
    }


    @GetMapping("/Services/filterBySlug")

    public ResponseEntity<List<Services>>getServicesBySlug(@RequestParam String slug){
        return  new ResponseEntity<List<Services>>(servicesServices.getServicesBySlug(slug),HttpStatus.OK);
    }


    @GetMapping("/Services/filterByDescription")

    public ResponseEntity<List<Services>>getServicesByDescription(@RequestParam String description){

        return new ResponseEntity<List<Services>>(servicesServices.getServicesByDescription(description),HttpStatus.OK);
    }




    @GetMapping("/Services/filterByCategory")
    public ResponseEntity<List<Services>>getServicesByCategory(@RequestParam int category_id){

        return  new ResponseEntity<List<Services>>(servicesServices.getServicesByCategory(category_id),HttpStatus.OK);
    }



    @GetMapping("/Services/findByRegionId")

    public ResponseEntity<List<Services>>getServicesByRegion(@RequestParam String region_id){

        return  new ResponseEntity<List<Services>>(servicesServices.getServicesByRegion_id(region_id),HttpStatus.OK);
    }


    @DeleteMapping("/Services/DeleteByDescription")
    public void deleServices(@RequestParam String description) {
        servicesServices.getServicesByDescription(description);
}


    @PutMapping("/Services/{id}")
    public ResponseEntity<Services> SaveServices(@PathVariable Long id, @RequestBody Services services) {
        services.setId(id);
        return new ResponseEntity<Services>(servicesServices.updateService(services), HttpStatus.OK);

    }



@GetMapping ("/Services/findViewsWithSorting")
   public ResponseEntity<List<Services>>findViewsWithSorting(@RequestParam int views){
        return new ResponseEntity<List<Services>>(servicesServices.findViewsWithSortingAsc(),HttpStatus.OK);


}
}
