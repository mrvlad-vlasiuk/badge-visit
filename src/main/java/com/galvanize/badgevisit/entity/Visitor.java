package com.galvanize.badgevisit.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Visitor {
    private Long phoneNumber;

    private String firstName;
    private String lastName;
    private String company;

    @Builder
    public Visitor(Long phoneNumber, String firstName, String lastName, String company) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }
}
