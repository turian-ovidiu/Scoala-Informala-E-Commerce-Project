package com.Ecommerce.domain;

import javax.persistence.Embeddable;

/**
 * Created by Ovi on 4/11/2017.
 */
@Embeddable
public class AddressShipping {

    private String addressLine1;
    private String addressLine2;
    private String cityShipping;
    private String stateShipping;
    private String zipCodeShipping;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCityShipping() {
        return cityShipping;
    }

    public void setCityShipping(String cityShipping) {
        this.cityShipping = cityShipping;
    }

    public String getStateShipping() {
        return stateShipping;
    }

    public void setStateShipping(String stateShipping) {
        this.stateShipping = stateShipping;
    }

    public String getZipCodeShipping() {
        return zipCodeShipping;
    }

    public void setZipCodeShipping(String zipCodeShipping) {
        this.zipCodeShipping = zipCodeShipping;
    }
}
