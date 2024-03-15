package Valerio;

public class Book extends Catalog {

    private String author;
    private String type;


    public Book(int codeIsbn, String title, int publicationYear, int numberPage, String author, String type) {
        super(codeIsbn, title, publicationYear, numberPage);
        this.author = author;
        this.type = type;
        addPublication(this);
    }


    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    @Override
    public void details() {
        System.out.println("Title -" + getTitle());
        System.out.println("Author -" + getAuthor());
        System.out.println("Type -" + getType());
        System.out.println("Publication Year -" + getPublicationYear());
        System.out.println("Number Page -" + getNumberPage());
        System.out.println("ISBN -" + getCodeIsbn());
    }

    //    implementato per salvare il contenuto fuori dal programma
    public String toStringDetails() {
        return "Book" +
                "Title='" + getTitle() + '\'' +
                ", Author='" + getAuthor() + '\'' +
                ", Type='" + getType() + '\'' +
                ", Publication Year=" + getPublicationYear() +
                ", Number Page=" + getNumberPage() +
                ", ISBN=" + getCodeIsbn()
                ;
    }


}
