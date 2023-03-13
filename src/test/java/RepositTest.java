import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RepositTest {
    Reposit repo = new Reposit();
    Product book1 = new Book(1, "the alchemist", 100, "Coelho");
    Product book2 = new Book(2, "azazel", 200, "Akunin");
    Product book3 = new Book(3, "the viy", 300, "Gogol");
    Product smartphone = new Smartphone(4, "iPhone", 90000, "Apple");
    Product smartphone2 = new Smartphone(5, "Galaxy", 50_000, "Samsung");

    @Test
    public void testRemoveWhenProductExist() {

        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        repo.add(smartphone);
        repo.removeById(1);
        Product[] actual = repo.findAll();
        Product[] expected = {book2, book3, smartphone };
        Assertions.assertArrayEquals(expected, actual);

    }
    @Test
    public void shouldFindAll() {
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        repo.add(smartphone);
        Product[] expected = {book1, book2, book3, smartphone};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveById() {
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        repo.add(smartphone);
        repo.removeById(2);
        Product[] expected = { book1, book3, smartphone };
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveAllById() {
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        repo.add(smartphone);
        repo.removeById(1);
        repo.removeById(2);
        repo.removeById(3);
        repo.removeById(4);
        Product[] expected = {};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}