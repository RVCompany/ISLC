package com.drozd.forms;

import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarDeliveryRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CarDeliveryRequestForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private Long carId;

    @NotNull
    @Min(0)
    @Max(100)

    private Double advance;

    @NotNull
    @Min(0)
    @Max(100)
    private Double rate;

    @NotNull
    @Min(0)
    @Max(100)
    private Double remainder;

    private String paymentSchedule;

    private String paymentFrequency;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public CarDeliveryRequest createRequest(Car car) {
        return new CarDeliveryRequest(car, advance, rate, remainder, paymentSchedule, paymentFrequency);
    }
}
