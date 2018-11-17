package main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SellObject {
    String name;
    BigDecimal quantity;
    String unit;
    BigDecimal unitBruttoPrice;
    BigDecimal unitNettoPrice;
    int vat;
    BigDecimal nettoPrice;
    BigDecimal vatAmount;
    BigDecimal bruttoPeice;


    public SellObject(String name, BigDecimal quantity, String unit, BigDecimal unitBruttoPrice, int vat) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.unitBruttoPrice = unitBruttoPrice;
        this.vat = vat;
        unitNettoPrice = unitBruttoPrice.divide(new BigDecimal((vat + 100.00) / 100.00), BigDecimal.ROUND_DOWN);
        nettoPrice = unitNettoPrice.multiply(quantity);
        nettoPrice = nettoPrice.setScale(2, BigDecimal.ROUND_DOWN);
        vatAmount = nettoPrice.multiply(BigDecimal.valueOf(vat / 100.00));
        vatAmount = vatAmount.setScale(2, BigDecimal.ROUND_UP);
        bruttoPeice = unitBruttoPrice.multiply(quantity);
        bruttoPeice = bruttoPeice.setScale(2, BigDecimal.ROUND_DOWN);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitBruttoPrice() {
        return unitBruttoPrice;
    }

    public void setUnitBruttoPrice(BigDecimal unitBruttoPrice) {
        this.unitBruttoPrice = unitBruttoPrice;
    }

    public BigDecimal getUnitNettoPrice() {
        return unitNettoPrice;
    }

    public void setUnitNettoPrice(BigDecimal unitNettoPrice) {
        this.unitNettoPrice = unitNettoPrice;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public BigDecimal getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(BigDecimal nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getBruttoPeice() {
        return bruttoPeice;
    }

    public void setBruttoPeice(BigDecimal bruttoPeice) {
        this.bruttoPeice = bruttoPeice;
    }

    @Override
    public String toString() {
        return "SellObject{" +
                "name='" + name + '\'' +
                ",\n quantity=" + quantity +
                ",\n unit='" + unit + '\'' +
                ",\n unitBruttoPrice=" + unitBruttoPrice +
                ",\n unitNettoPrice=" + unitNettoPrice +
                ",\n vat=" + vat +
                ",\n nettoPrice=" + nettoPrice +
                ",\n vatAmount=" + vatAmount +
                ",\n bruttoPeice=" + bruttoPeice +
                '}';
    }
}
