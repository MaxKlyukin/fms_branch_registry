package maxklyukin.me.fms_branch_registry.remote_csv_fetcher;

import maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher.ArchiveExtractor;
import maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher.FileDownloader;
import maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher.RegistryCSVReader;
import maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher.RemoteCSVRegistryFetcher;
import maxklyukin.me.fms_branch_registry.registry.RegistryFetchException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RemoteCSVRegistryFetcherTest {

    private FileDownloader fileDownloader;
    private ArchiveExtractor archiveExtractor;
    private RegistryCSVReader reader;

    private RemoteCSVRegistryFetcher fetcher;

    @Before
    public void createFetcher() {
        fileDownloader = mock(FileDownloader.class);
        archiveExtractor = mock(ArchiveExtractor.class);
        reader = mock(RegistryCSVReader.class);

        fetcher = new RemoteCSVRegistryFetcher(fileDownloader, archiveExtractor, reader);
    }

    @Test
    public void it_downloads_extracts_and_reads() throws IOException, RegistryFetchException {
        String link = "abc";
        String filePath = "def";

        fetcher.fetch(link, filePath);

        verify(fileDownloader).download(link);
        verify(archiveExtractor).extractFile(null, filePath);
        verify(reader).readEntries(null);
    }

    @Test
    public void it_deletes_file_after_fetching() throws IOException, RegistryFetchException {
        File file = File.createTempFile("fetcher_test", "");
        when(fileDownloader.download("abc")).thenReturn(file);

        fetcher.fetch("abc", null);

        assertFalse(file.exists());
    }

    @Test(expected = RegistryFetchException.class)
    public void it_converts_and_passes_exception_along() throws IOException, RegistryFetchException {
        when(fileDownloader.download("abc")).thenThrow(IOException.class);
        fetcher.fetch("abc", null);
    }
}
