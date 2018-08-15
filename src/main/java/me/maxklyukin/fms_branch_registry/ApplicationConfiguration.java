package me.maxklyukin.fms_branch_registry;

import me.maxklyukin.fms_branch_registry.infrastructure.remote_csv_fetcher.RemoteCSVRegistryFetcherFactory;
import me.maxklyukin.fms_branch_registry.registry.*;
import me.maxklyukin.fms_branch_registry.registry.RegistryFetcher;
import me.maxklyukin.fms_branch_registry.registry.RegistryRepository;
import me.maxklyukin.fms_branch_registry.registry.RegistryUpdater;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("me.maxklyukin.fms_branch_registry")
public class ApplicationConfiguration {

    @Value("${app.db_link}")
    private String dbLink;
    @Value("${app.file_path}")
    private String filePath;
    @Value("${app.charset}")
    private String charset;

    @Bean
    public RemoteCSVRegistryFetcherFactory fetcherFactory() {
        return new RemoteCSVRegistryFetcherFactory();
    }

    @Bean
    public RegistryFetcher fetcher(RemoteCSVRegistryFetcherFactory factory) {
        return factory.create(dbLink, filePath, charset);
    }

    @Bean
    public RegistryUpdater updater(RegistryFetcher fetcher, RegistryRepository repository) {
        return new RegistryUpdater(fetcher, repository);
    }
}
