package com.campify.carsinventaire;

//import com.campify.carsinventaire.config.ConfigParams;
import com.campify.carsinventaire.config.ConfigParams;
import com.campify.carsinventaire.domain.entities.CarsEntity;
import com.campify.carsinventaire.repository.CarsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({ConfigParams.class})
public class CarsInventaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsInventaireApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CarsRepository carsRepository){
        return args -> {
            carsRepository.save(CarsEntity.builder()
                    .brand("Toyota")
                    .name("Corolla")
                    .imagepath("../images/cars-big/audia1.jpg")
                    .dimensions("4500mm x 1725mm x 1460mm")
                    .puissance("120 hp")
                    .type("Family")
                    .dateFabrication("2023")
                    .transmission("Automatic")
                    .price(250)
                    .build());

            carsRepository.save(CarsEntity.builder()
                    .brand("Honda")
                    .name("Civic")
                    .imagepath("../images/cars-big/golf6.jpg")
                    .dimensions("4550mm x 1800mm x 1435mm")
                    .puissance("140 hp")
                    .type("Sport")
                    .dateFabrication("2022")
                    .transmission("Manual")
                    .price(350)
                    .build());

            carsRepository.save(CarsEntity.builder()
                    .brand("BMW")
                    .name("X5")
                    .imagepath("../images/cars-big/toyotacamry.jpg")
                    .dimensions("4922mm x 2004mm x 1745mm")
                    .puissance("300 hp")
                    .type("Luxury")
                    .dateFabrication("2023")
                    .transmission("Automatic")
                    .price(850)
                    .build());

            carsRepository.save(CarsEntity.builder()
                    .brand("Ford")
                    .name("Mustang")
                    .imagepath("path/to/mustang.jpg")
                    .dimensions("4788mm x 1915mm x 1381mm")
                    .puissance("450 hp")
                    .type("Sport")
                    .dateFabrication("2022")
                    .transmission("Manual")
                    .price(550)
                    .build());

            carsRepository.save(CarsEntity.builder()
                    .brand("Audi")
                    .name("Q7")
                    .imagepath("path/to/q7.jpg")
                    .dimensions("5052mm x 1968mm x 1741mm")
                    .puissance("280 hp")
                    .type("SUV")
                    .dateFabrication("2023")
                    .transmission("Automatic")
                    .price(750)
                    .build());
        };
    }
}
