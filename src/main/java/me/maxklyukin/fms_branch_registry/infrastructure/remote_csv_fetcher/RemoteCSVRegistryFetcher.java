package me.maxklyukin.fms_branch_registry.infrastructure.remote_csv_fetcher;

import me.maxklyukin.fms_branch_registry.registry.Branch;
import me.maxklyukin.fms_branch_registry.registry.RegistryFetchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RemoteCSVRegistryFetcher {

    private final static Logger logger = LoggerFactory.getLogger(RemoteCSVRegistryFetcher.class);

    private final FileDownloader fileDownloader;
    private final ArchiveExtractor archiveExtractor;
    private final RegistryCSVReader reader;

    public RemoteCSVRegistryFetcher(FileDownloader fileDownloader, ArchiveExtractor archiveExtractor, RegistryCSVReader reader) {
        this.fileDownloader = fileDownloader;
        this.archiveExtractor = archiveExtractor;
        this.reader = reader;
    }

    public List<Branch> fetch(String link, String filePath) throws RegistryFetchException {
        File archive = null;
        File file = null;
        try {
            archive = fileDownloader.download(link);
            file = archiveExtractor.extractFile(archive, filePath);
            return reader.readEntries(file);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RegistryFetchException("Registry can not be fetched, it is unavailable or invalid", e);
        } finally {
            if (file != null) file.delete();
            if (archive != null) archive.delete();
        }
    }
}
