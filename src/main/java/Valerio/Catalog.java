package Valerio;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Catalog {

    private static ArrayList<Catalog> catalogList = new ArrayList<Catalog>();

    private int codeIsbn;
    private String title;
    private int publicationYear;
    private int numberPage;

    public Catalog(int codeIsbn, String title, int publicationYear, int numberPage) {
        this.codeIsbn = codeIsbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberPage = numberPage;
    }

    public static void addPublication(Catalog publication) {
        catalogList.add(publication);
    }

    //----------------------------------RIMOZIONE----------------------------------
    public static void removeByIsbn(int codeIsbn) {
        Iterator<Catalog> iterator = catalogList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Catalog publication = iterator.next();
            if (publication.getCodeIsbn() == codeIsbn) {
                iterator.remove();
                System.out.println("pubblicazione: " + codeIsbn + " " + "trovata e rimossa");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("pubblicazione: " + codeIsbn + " " + "non trovata");
        }
    }

    // ------------------------------RICERCA-----------------------------


    //    RICERCA TRAMIRE CODICE ISBN
    public static void searchByIsbn(int codeIsbn) {
        Iterator<Catalog> iterator = catalogList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Catalog publication = iterator.next();
            if (publication.getCodeIsbn() == codeIsbn) {
                System.out.println("pubblicazione: " + codeIsbn + " " + "trovata");
                System.out.println();
                System.out.println("------------------DETTAGLI PUBBLICAZIONE----------------");
                publication.details();
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("pubblicazione: " + codeIsbn + " " + "non trovata");
        }
    }

    //    RICERCA TRAMITE ANNO DI PUBBLICAZIONE
    public static void searchByPublicationYear(int year) {
        List<Catalog> publications = catalogList.stream().filter(publication -> publication.getPublicationYear() == year).collect(Collectors.toList());
        if (!publications.isEmpty()) {
            System.out.println("pubblicazioni per l'anno: " + year + " " + "trovati");
            for (Catalog publication : publications) {
                publication.details();
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("nessuna pubblicazione trovata per l'anno: " + year);
        }
    }

    //RICERCA TRAMITE AUTORE
    public static void searchByAuthor(String author) {
        List<Catalog> publications = catalogList.stream()
                .filter(book -> book instanceof Book && ((Book) book).getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());

        if (!publications.isEmpty()) {
            System.out.println("pubblicazione per autore: " + author + " " + "trovati");
            for (Catalog publication : publications) {
                publication.details();
                System.out.println("--------------------");
            }
        }
    }


//    --------------------------------SALVA SU DISCO---------------------------

    public static void saveCatalog(String nameFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile))) {
            for (Catalog publication : catalogList) {
                writer.write(publication.toStringDetails());
                writer.newLine();
            }
            System.out.println("Dati salvati correttamente sul file: " + nameFile);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dei dati");
        }
    }


//     -------------------- IMPORTA DA DISCO---------------

    public static void lodCatalog(String nameFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Catalog publication = createPublicationFromLine(line);
                if (publication != null) {
                    catalogList.add(publication);
                }
            }
            System.out.println("Dati caricati correttamente dal file: " + nameFile);
        } catch (IOException e) {
            System.out.println("errore durante il caricamento dei dati: " + e.getMessage());
        }
    }

    //-----------------------------STAMPA-------------------------------
    public static void printAllPublication() {
        System.out.println("List of publications");
        for (Catalog publication : catalogList) {
            publication.details();
            System.out.println();
        }
    }

    private static Catalog createPublicationFromLine(String line) {
        if (line.startsWith("Book")) {
            String[] parts = line.split(", ");
            if (parts.length >= 6) {
                int codeIsbn = Integer.parseInt(parts[5].split("=")[1]);
                String title = parts[0].split("=")[1];
                int publicationYear = Integer.parseInt(parts[3].split("=")[1]);
                int numberPage = Integer.parseInt(parts[4].split("=")[1]);
                String author = parts[1].split("=")[1];
                String type = parts[2].split("=")[1];
                return new Book(codeIsbn, title, publicationYear, numberPage, author, type);
            }
        } else if (line.startsWith("Magazine")) {
            String[] parts = line.split(", ");
            if (parts.length >= 5) {
                int codeIsbn = Integer.parseInt(parts[4].split("=")[1]);
                String title = parts[0].split("=")[1];
                int publicationYear = Integer.parseInt(parts[1].split("=")[1]);
                int NumberPage = Integer.parseInt(parts[3].split("=")[1]);
                PeriodicityMagazine periodicity = PeriodicityMagazine.valueOf(parts[2].split("=")[1]);
                return new Magazine(codeIsbn, title, publicationYear, NumberPage, periodicity);
            }
        }
        return null;
    }


    public int getCodeIsbn() {
        return codeIsbn;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public abstract void details();

    public abstract String toStringDetails();
}
