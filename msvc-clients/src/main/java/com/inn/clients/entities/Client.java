package com.inn.clients.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_last_name")
    private String clientLastName;
    @Column(name = "client_email")
    private String clientEmail;
    @Column(name = "client_phone")
    private String clientPhone;
}