package guru.springframework.bootstrap;

import guru.springframework.domain.Author;
import guru.springframework.domain.Book;
import guru.springframework.domain.Publisher;
import guru.springframework.repositories.AuthorRepository;
import guru.springframework.repositories.BookRepository;
import guru.springframework.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");
        Publisher publisher = new Publisher();
        publisher.setName("Blue Dome");
        publisher.setCity("Paterson");
        publisher.setState("NJ");
        publisherRepository.save(publisher);

        System.out.println("Publisher count => " + publisherRepository.count());


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        ddd.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rodd = new Author("Rodd", "Johnson");
        Book jee = new Book("J2EE Developers", "123erert456");
        eric.getBooks().add(jee);
        ddd.getAuthors().add(rodd);
        jee.setPublisher(publisher);
        publisher.getBooks().add(jee);

        authorRepository.save(rodd);
        bookRepository.save(jee);
       publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books => "+ bookRepository.count());
        System.out.println("Publisher NUmber of Books =>" + publisher.getBooks().size());
    }
}
