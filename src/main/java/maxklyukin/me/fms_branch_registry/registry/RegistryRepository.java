package maxklyukin.me.fms_branch_registry.registry;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RegistryRepository extends Repository<Branch, String> {

    Optional<Branch> findByCode(String code);

    void deleteAllInBatch();
    void saveAll(Iterable<Branch> branches);

    @Transactional
    default void replaceAll(List<Branch> branches) {
        deleteAllInBatch();
        saveAll(branches);
    }
}
