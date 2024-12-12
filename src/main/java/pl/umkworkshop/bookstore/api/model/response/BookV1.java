package pl.umkworkshop.bookstore.api.model.response;

public record BookV1(Long id,
                     String title,
                     AuthorV1 author,
                     int stock,
                     DescriptionV1 description) implements Comparable<BookV1> {

    @Override
    public int compareTo(BookV1 o) {
        long compare = this.id - o.id;
        if (compare > 0) {
            return 1;
        } else if (compare < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}