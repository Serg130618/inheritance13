import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManagerTest {
    Reposit repo = new Reposit();
    Manager manager = new Manager(repo);
    Product book1 = new Book(1, "the alchemist", 100, "Coelho");
    Product book2 = new Book(2, "azazel", 200, "Akunin");
    Product book3 = new Book(3, "the viy", 300, "Gogol");
    Product book4 = new Book(6, "the viy2", 400, "Gogol");
    Product book5 = new Book(7, "the viy3", 400, "Gogol");

    Product smartphone = new Smartphone(4, "iPhone", 90000, "Apple");
    Product smartphone2 = new Smartphone(5, "Galaxy", 50_000, "Samsung");

    @BeforeEach
    public void SetUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone);
        manager.add(smartphone2);
    }
    @Test
    public void testAdd() {

        Product[] actual = repo.findAll();
        Product[] expected = {book1, book2, book3, book4, smartphone,smartphone2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //один товар
    public void testSearchWhenProductsFound() {

        Product[] actual = manager.searchBy("azazel");
        Product[] expected = {book2};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test //находится 0 товаров
    public void testSearchWhenProductsMiss() {

        Product[] actual = manager.searchBy("se");
        Product[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test // находится несколько товаров
    public void testSearchWhenFewProducts() {

        Product[] actual = manager.searchBy("viy");
        Product[] expected = {book3, book4};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchByPhoneName() {

        String name = "iPhone";
        Product[] expected = {smartphone};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindPhoneManufacturer() {

        String manufacturer = "Apple";
        Product[] expected = {};
        Product[] actual = manager.searchBy(manufacturer);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldNotFindAuthor() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Akunin");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchNameOrAuthor() {

        String author = "Gogol";
        Product[] expected = {};
        Product[] actual = manager.searchBy(author);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
        //Тест поиска книги по названию которой нет в списке
    public void shouldFindNameNotExistBook() {
        manager.add(book2);
        Product[] actual = manager.searchBy("Руслан и Людмила");
        Product[] expected = new Product[]{};
        Assertions.assertArrayEquals(actual, expected);
    }
    @Test // Тест добавления еще одного элемента
    public void shouldAddOneMore() {
        manager.add(book5);
        Product[] actual = repo.findAll();
        Product[] expected = new Product[]{book1, book2, book3, book4, smartphone, smartphone2, book5};
        Assertions.assertArrayEquals(actual, expected);

    }
}