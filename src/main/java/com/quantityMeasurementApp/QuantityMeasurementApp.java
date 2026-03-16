package com.quantityMeasurementApp;
import com.quantityMeasurementApp.controller.QuantityMeasurementController;
import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurementApp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementCacheRepository repo =
                QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(12,"INCH","LENGTH");

        controller.performAddition(q1,q2);
    }
}