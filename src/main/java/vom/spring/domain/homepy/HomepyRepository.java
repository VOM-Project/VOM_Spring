package vom.spring.domain.homepy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class HomepyRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Homepy homepy) {
        em.persist(homepy);
    }

    public Homepy findByUser(User user) {
        return em.find(Homepy.class, user);
    }
}