package com.drozd.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "carDeliveryRequest")
public class CarDeliveryRequest {

    @Id
    @GeneratedValue
    @Column(name = "requestId", unique = true, nullable = false)
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = true)
    private Car car;

    @Column
    private Double advance;

    @Column
    private Integer period;

    @Column
    private Double rate;

    @Column
    private Double remainder;

    @Column
    private String paymentSchedule;




    public CarDeliveryRequest(Car car, Double advance, Integer period, Double rate, Double remainder, String paymentSchedule, String paymentFrequency) {
        this.car = car;
        this.advance = advance;
        this.period = period;
        this.rate = rate;
        this.remainder = remainder;
        this.paymentSchedule = paymentSchedule;
        this.paymentFrequency = paymentFrequency;
    }


    @Column
    private String paymentFrequency;

    public CarDeliveryRequest() {
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public Integer getPeriod() { return period; }

    public void setPeriod(Integer period) { this.period = period; }

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
}
