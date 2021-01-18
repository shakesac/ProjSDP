package pt.ual.sdp.pl3.StockManagement.models;

import java.util.HashMap;

public class Delivery {

    private int id;
    private String address;
    private HashMap<Item, Integer> items;

    public Delivery(String address, HashMap<Item, Integer> items) {
        this.address = address;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Item, Integer> items) {
        this.items = items;
    }

}
