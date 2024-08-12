package com.example.api_user.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Construtor padrão
    public Users() {
    }

    // Construtor com parâmetros
    public Users(Long id, String nome, String email, Department department) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.department = department;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
