package maxklyukin.me.fms_branch_registry.registry;

public interface RegistryFetcherFactory {

    RegistryFetcher create(String link, String filePath, String charset);
}
