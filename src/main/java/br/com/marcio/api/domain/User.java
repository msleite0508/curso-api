package br.com.marcio.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Data //gera todos os metodos - equals...
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;



}
