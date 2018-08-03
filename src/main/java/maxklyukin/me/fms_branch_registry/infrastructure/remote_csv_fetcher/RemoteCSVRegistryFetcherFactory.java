package maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher;

import maxklyukin.me.fms_branch_registry.registry.RegistryFetcher;
import maxklyukin.me.fms_branch_registry.registry.RegistryFetcherFactory;

public class RemoteCSVRegistryFetcherFactory implements RegistryFetcherFactory {

    @Override
    public RegistryFetcher create(String link, String filePath, String charset) {
        FileDownloader fileDownloader = new FileDownloader();
        ArchiveExtractor archiveExtractor = new ArchiveExtractor();
        RegistryCSVReader reader = new RegistryCSVReader(charset);

        RemoteCSVRegistryFetcher remoteCSVFetcher = new RemoteCSVRegistryFetcher(fileDownloader, archiveExtractor, reader);

        return () -> remoteCSVFetcher.fetch(link, filePath);
    }
}
