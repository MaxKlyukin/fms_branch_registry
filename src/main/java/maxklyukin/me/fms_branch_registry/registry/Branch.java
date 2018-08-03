package maxklyukin.me.fms_branch_registry.registry;

import lombok.AllArgsConstructor;
import lombok.Value;
import maxklyukin.me.fms_branch_registry.http.Response;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Value
@Entity
@AllArgsConstructor
@Table(name = "fms_branches")
public class Branch implements Response {

    @Id
    private String code;
    private String name;

    Branch() {
        code = null;
        name = null;
    }
}
