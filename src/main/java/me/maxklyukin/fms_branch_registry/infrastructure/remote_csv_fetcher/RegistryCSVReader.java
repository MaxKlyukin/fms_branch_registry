package me.maxklyukin.fms_branch_registry.infrastructure.remote_csv_fetcher;

import me.maxklyukin.fms_branch_registry.registry.Branch;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RegistryCSVReader {

    private String charset;

    public RegistryCSVReader(String charset) {
        this.charset = charset;
    }

    public List<Branch> readEntries(File file) throws IOException {
        try (Reader in = new InputStreamReader(new FileInputStream(file), Charset.forName(charset))) {
            return parseEntities(in);
        } catch (FileNotFoundException e) {
            return null;//this will never happen
        }
    }

    private List<Branch> parseEntities(Reader in) throws IOException {
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces().parse(in);

        return StreamSupport.stream(records.spliterator(), false)
                .skip(2)//skip meta line and headers line
                .map(this::recordToRegistryEntry)
                .collect(Collectors.toList());
    }

    private Branch recordToRegistryEntry(CSVRecord record) {
        return new Branch(record.get(1), record.get(0));
    }
}
