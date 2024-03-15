package Valerio;

public class Application {
    public static void main(String[] args) {
        Magazine magazine = new Magazine(123456789, "New Yok Times", 2019, 25, PeriodicityMagazine.WEEKLY);
        Magazine magazine1 = new Magazine(345678912, "Il Messaggero", 2024, 15, PeriodicityMagazine.WEEKLY);
        Magazine magazine2 = new Magazine(456789123, "Aeronautica & Difesa", 2024, 30, PeriodicityMagazine.MONTHLY);
        Book book = new Book(234567891, "Harry Potter e l'ordine della fenice", 2005, 575, "J.K.Rowling", "Fantasy");
        Book book1 = new Book(567891234, "il Signore degli anelli e le due torri", 2000, 450, "J. R. R. Tolkien", "Fantasy");
        Book book2 = new Book(567891234, "il Signore degli anelli e la compagnia dell'anello", 2000, 450, "J. R. R. Tolkien", "Fantasy");

//        Catalog.removeByIsbn(567891234);

//        Catalog.searchByIsbn(567891234);
//        Catalog.searchByPublicationYear(2024);
//        Catalog.searchByAuthor("J.K.Rowling");

//        Catalog.saveCatalog("elenco-pubblicazioni.txt");
//        Catalog.lodCatalog("elenco-pubblicazioni.txt");

//        Catalog.printAllPublication();


    }
}
