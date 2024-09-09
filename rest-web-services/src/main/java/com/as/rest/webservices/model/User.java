package com.as.rest.webservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Entity
public class User {
    @Id
    private int id;
    private String name;
    private LocalDate dateOfBirth;

}
