package com.campify.carsinventaire.clients;


import com.campify.carsinventaire.model.Inventaire;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTAIRE-SERVICE")
public interface InventaireRestClient {

    @GetMapping("inventaire/cars/{id}")
    @CircuitBreaker(name = "inventaireService" , fallbackMethod = "getListInventaire") // design pattern
    List<Inventaire> listInventaireByVoiture(@PathVariable int id);

 // axios.get(link)
    @GetMapping("inventaire/disponible/{id}")
    @CircuitBreaker(name = "inventaireService" , fallbackMethod = "getListDispo")
    List<Inventaire> listInventaireByDisponible(@PathVariable int id);

    default List<Inventaire> getListInventaire(int id , Exception e){
        return List.of();
    }

    default List<Inventaire> getListDispo(int id , Exception e){
        return List.of();
    }

}
