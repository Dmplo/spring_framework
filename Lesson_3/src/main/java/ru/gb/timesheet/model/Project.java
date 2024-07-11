package ru.gb.timesheet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Project {
    Long id;
    String name;

    public Project(String name) {
        this.name = name;
    }
}
