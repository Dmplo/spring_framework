package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserRole> users = new ArrayList<>();

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
