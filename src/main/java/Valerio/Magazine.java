package Valerio;

public class Magazine extends Catalog {
    private PeriodicityMagazine periodicity;

    public Magazine(int codeIsbn, String title, int publicationYear, int numberPage, PeriodicityMagazine periodicity) {
        super(codeIsbn, title, publicationYear, numberPage);
        this.periodicity = periodicity;
        addPublication(this);
    }

    public void details() {
        System.out.println("Title -" + getTitle());
        System.out.println("Publication Year -" + getPublicationYear());
        System.out.println("Periodicity -" + periodicity.name());
        System.out.println("Number Page -" + getNumberPage());
        System.out.println("ISBN -" + getCodeIsbn());
    }

    public String toStringDetails() {
        return "Magazine" +
                "Title='" + getTitle() + '\'' +
                ", Publication Year=" + getPublicationYear() +
                ", Periodicity=" + periodicity +
                ", Number Page=" + getNumberPage() +
                ", ISBN=" + getCodeIsbn();
    }


}
