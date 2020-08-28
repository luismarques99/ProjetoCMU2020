package com.cmuteam.app;

public class Establishment {
    private int establishment_id;
    private String establishment_name;

    public Establishment(int establishment_id, String establishment_name) {
        this.establishment_id = establishment_id;
        this.establishment_name = establishment_name;

    }

    public int getId() {
        return establishment_id;
    }

    public String getName() {
        return establishment_name;
    }
}
