package me.maxklyukin.fms_branch_registry.infrastructure.remote_csv_fetcher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileDownloader {

    public File download(String link) throws IOException {
        URL url = new URL(link);

        try (InputStream source = url.openStream()) {
            File file = File.createTempFile(url.getPath(), "");
            Files.copy(source, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return file;
        }
    }
}
