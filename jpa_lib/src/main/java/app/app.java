package app;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class app {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_lib");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Aggiunta elemento al catalogo
            Libro libro = new Libro();
            libro.setIsbn("8817079761");
            libro.setTitolo("La teoria del tutto");
            libro.setAnnoPubblicazione(2002);
            libro.setNumeroPagine(150);
            libro.setAutore("Steaphen Hawking");
            libro.setGenere("Scienza");

            entityManager.getTransaction().begin();
            entityManager.persist(libro);
            entityManager.getTransaction().commit();

            // Ricerca per ISBN
            Libro libroTrovato = entityManager.find(Libro.class, "8817079761");
            System.out.println("Libro trovato: " + libroTrovato.getTitolo());

            // Ricerca per anno di pubblicazione
            TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.annoPubblicazione = :anno", Libro.class);
            query.setParameter("anno", 2002);
            List<Libro> libriAnno2002 = query.getResultList();
            System.out.println("Libri pubblicati nel 2002:");
            for (Libro l : libriAnno2002) {
                System.out.println("- " + l.getTitolo());
            }

            // Creazione utente
            Utente utente = new Utente();
            utente.setNome("Andrea");
            utente.setCognome("Caforio");
            utente.setDataNascita(new Date());
            utente.setNumeroTessera("2241");

            entityManager.getTransaction().begin();
            entityManager.persist(utente);
            entityManager.getTransaction().commit();

            // Esempio prestito
            Prestito prestito = new Prestito();
            prestito.setUtente(utente);
            prestito.setElementoPrestato(libro);
            prestito.setDataInizioPrestito(new Date());

            entityManager.getTransaction().begin();
            entityManager.persist(prestito);
            entityManager.getTransaction().commit();

            // Ricerca degli elementiin prestito utente
            TypedQuery<Prestito> prestitiUtenteQuery = entityManager.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera", Prestito.class);
            prestitiUtenteQuery.setParameter("tessera", "2241");
            List<Prestito> prestitiUtente = prestitiUtenteQuery.getResultList();
            System.out.println("Prestiti dell'utente:");
            for (Prestito p : prestitiUtente) {
                System.out.println("- " + p.getElementoPrestato().getTitolo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
