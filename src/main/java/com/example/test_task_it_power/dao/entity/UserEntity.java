package com.example.test_task_it_power.dao.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks;
}
