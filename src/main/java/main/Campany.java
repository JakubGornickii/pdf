package main;

public class Campany {
    private String name;
    private String adressLine1;
    private String adressLine2;
    private String nip;

    public Campany(String name, String adressLine1, String adressLine2, String nip) {
        this.name = name;
        this.adressLine1 = adressLine1;
        this.adressLine2 = adressLine2;
        this.nip = nip;
    }

    public Campany(String name, String adressLine2, String nip) {
        this.name = name;
        this.adressLine2 = adressLine2;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdressLine1() {
        return adressLine1;
    }

    public void setAdressLine1(String adressLine1) {
        this.adressLine1 = adressLine1;
    }

    public String getAdressLine2() {
        return adressLine2;
    }

    public void setAdressLine2(String adressLine2) {
        this.adressLine2 = adressLine2;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
