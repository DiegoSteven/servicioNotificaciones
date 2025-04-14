package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Device {

    private long id;
    private String name;
    private String uniqueId;

    // (Opcional: para mensajes más completos)
    private String phone;
    private String model;
    private String contact;

    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }


    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
