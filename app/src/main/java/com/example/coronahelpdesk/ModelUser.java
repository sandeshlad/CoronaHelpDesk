package com.example.coronahelpdesk;

public class ModelUser {

    String id;
    String name;
    String phone;
    String address;
    String list;

    public ModelUser() {

    }

    public ModelUser(String id, String name, String phone, String address, String list) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
