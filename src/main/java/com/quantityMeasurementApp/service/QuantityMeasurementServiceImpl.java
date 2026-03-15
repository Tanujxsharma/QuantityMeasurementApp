package com.quantityMeasurementApp.service;

import com.quantityMeasurementApp.domain.*;
import com.quantityMeasurementApp.dto.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo){
        this.repo = repo;
    }

    private IMeasurable resolveUnit(QuantityDTO dto){

        switch (dto.getMeasurementType()) {

            case "LENGTH":
                return LengthUnit.valueOf(dto.getUnit());

            case "WEIGHT":
                return WeightUnit.valueOf(dto.getUnit());

            case "VOLUME":
                return VolumeUnit.valueOf(dto.getUnit());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(dto.getUnit());

            default:
                throw new RuntimeException("Invalid measurement");
        }
    }

    public QuantityMeasurementEntity add(QuantityDTO d1,QuantityDTO d2){
        try{
            IMeasurable u1 = resolveUnit(d1);
            IMeasurable u2 = resolveUnit(d2);

            Quantity q1 = new Quantity(d1.getValue(),u1);
            Quantity q2 = new Quantity(d2.getValue(),u2);

            Quantity result = q1.add(q2);

            QuantityMeasurementEntity e =
                    new QuantityMeasurementEntity("ADD",result.toString());

            repo.save(e);
            return e;
        }
        catch(Exception ex){
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    public QuantityMeasurementEntity subtract(QuantityDTO d1,QuantityDTO d2){
        try{
            Quantity q1 = new Quantity(d1.getValue(),resolveUnit(d1));
            Quantity q2 = new Quantity(d2.getValue(),resolveUnit(d2));

            Quantity result = q1.subtract(q2);

            QuantityMeasurementEntity e =
                    new QuantityMeasurementEntity("SUBTRACT",result.toString());

            repo.save(e);
            return e;
        }
        catch(Exception ex){
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    public QuantityMeasurementEntity divide(QuantityDTO d1,QuantityDTO d2){
        try{
            Quantity q1 = new Quantity(d1.getValue(),resolveUnit(d1));
            Quantity q2 = new Quantity(d2.getValue(),resolveUnit(d2));

            double result = q1.divide(q2);

            QuantityMeasurementEntity e =
                    new QuantityMeasurementEntity("DIVIDE",String.valueOf(result));

            repo.save(e);
            return e;
        }
        catch(Exception ex){
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    public QuantityMeasurementEntity convert(QuantityDTO d1,String target){
        try{
            IMeasurable t =
                    LengthUnit.valueOf(target);

            Quantity q =
                    new Quantity(d1.getValue(),resolveUnit(d1));

            Quantity result = q.convertTo(t);

            QuantityMeasurementEntity e =
                    new QuantityMeasurementEntity("CONVERT",result.toString());

            repo.save(e);
            return e;
        }
        catch(Exception ex){
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    public QuantityMeasurementEntity compare(QuantityDTO d1,QuantityDTO d2){
        try{
            Quantity q1 = new Quantity(d1.getValue(),resolveUnit(d1));
            Quantity q2 = new Quantity(d2.getValue(),resolveUnit(d2));

            boolean res = q1.equals(q2);

            QuantityMeasurementEntity e =
                    new QuantityMeasurementEntity("COMPARE",String.valueOf(res));

            repo.save(e);
            return e;
        }
        catch(Exception ex){
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }
}