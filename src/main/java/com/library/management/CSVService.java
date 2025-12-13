package com.library.management;

import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class CSVService {

    public void exportBooks(String filePath, LibraryManager lm) {
        try (FileWriter writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Id", "Title", "Author", "Issued"))) {

            for (Book b : lm.getBooks().values()) {
                csvPrinter.printRecord(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.isIssued()
                );
            }

            csvPrinter.flush();
            System.out.println("Books exported to CSV!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}