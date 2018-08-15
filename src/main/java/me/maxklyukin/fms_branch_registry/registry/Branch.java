package me.maxklyukin.fms_branch_registry.registry;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Value
@Entity
@AllArgsConstructor
@Table(name = "fms_branches")
public class Branch {

    @Id
    private String code;
    private String name;

    Branch() {
        code = null;
        name = null;
    }
}
