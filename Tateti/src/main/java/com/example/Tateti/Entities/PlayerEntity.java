package com.example.Tateti.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private String userName;
    @Column
    private String password;
}
