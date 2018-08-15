package me.maxklyukin.fms_branch_registry.registry;

import java.util.List;

public class RegistryUpdater {

    private final RegistryFetcher fetcher;
    private RegistryRepository registryRepo;

    public RegistryUpdater(RegistryFetcher fetcher, RegistryRepository registryRepo) {
        this.fetcher = fetcher;
        this.registryRepo = registryRepo;
    }

    public void update() throws RegistryUpdateException {
        List<Branch> branches = fetcher.fetch();
        registryRepo.replaceAll(branches);
    }
}
