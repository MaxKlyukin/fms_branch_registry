package me.maxklyukin.fms_branch_registry.registry;

import java.util.List;

public interface RegistryFetcher {

    List<Branch> fetch() throws RegistryFetchException;
}
