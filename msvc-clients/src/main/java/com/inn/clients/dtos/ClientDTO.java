package com.inn.clients.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO {

    private Long clientId;

    @NotBlank(message = "Client name is mandatory")
    @Size(max = 100, message = "Client name must be less than 100 characters")
    private String clientName;

    @NotBlank(message = "Client last name is mandatory")
    @Size(max = 100, message = "Client last name must be less than 100 characters")
    private String clientLastName;

    @NotBlank(message = "Client email is mandatory")
    @Email(message = "Email should be valid")
    private String clientEmail;

    @NotBlank(message = "Client phone is mandatory")
    @Size(max = 15, message = "Client phone must be less than 15 characters")
    private String clientPhone;
}