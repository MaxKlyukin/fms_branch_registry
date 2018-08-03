package maxklyukin.me.fms_branch_registry.infrastructure.remote_csv_fetcher;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ArchiveExtractor {

    public File extractFile(File archive, String filePath) throws IOException {
        try (ZipFile zipFile = new ZipFile(archive)) {
            return extractFromZip(zipFile, filePath);
        }
    }

    private File extractFromZip(ZipFile zipFile, String filePath) throws IOException {
        ZipEntry entry = zipFile.getEntry(filePath);
        File extractedFile = File.createTempFile(filePath, "");

        try (InputStream in = zipFile.getInputStream(entry);
             OutputStream out = new FileOutputStream(extractedFile)
        ) {
            IOUtils.copy(in, out);
        }

        return extractedFile;
    }
}
