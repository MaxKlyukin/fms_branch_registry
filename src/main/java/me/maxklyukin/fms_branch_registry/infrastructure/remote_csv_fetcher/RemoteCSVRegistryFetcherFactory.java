package me.maxklyukin.fms_branch_registry.infrastructure.remote_csv_fetcher;

import me.maxklyukin.fms_branch_registry.registry.RegistryFetcher;

public class RemoteCSVRegistryFetcherFactory {

    public RegistryFetcher create(String link, String filePath, String charset) {
        FileDownloader fileDownloader = new FileDownloader();
        ArchiveExtractor archiveExtractor = new ArchiveExtractor();
        RegistryCSVReader reader = new RegistryCSVReader(charset);

        RemoteCSVRegistryFetcher remoteCSVFetcher = new RemoteCSVRegistryFetcher(fileDownloader, archiveExtractor, reader);

        return () -> remoteCSVFetcher.fetch(link, filePath);
    }
}
