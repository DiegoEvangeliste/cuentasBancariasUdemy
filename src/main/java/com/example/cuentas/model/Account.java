package com.example.cuentas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="accounts")
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAccount")
    Integer idCuenta;

    @Column(name="name")
    String nombre;

    @Column(name="type")
    String tipoCuenta;

    @Column(name="balance")
    Double saldo;

}