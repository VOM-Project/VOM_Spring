package vom.spring.domain.touchpoint;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vom.spring.domain.album.Album;
import vom.spring.domain.member.domain.Member;

import java.util.List;

@Repository
public class TouchpointRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Touchpoint touchpoint) {
        em.persist(touchpoint);
    }

    public List<TouchpointDto> findByToMemberId(Long toMemberId, LocalDate today) {
        return em.createQuery(
                        "select new TouchpointDto(t.fromMember.nickname, t.createdAt, t.fromMember.profileImgUrl) " +
                                "from Touchpoint t " +
                                "where t.toMember.id = :toMemberId " +
                                "and t.createdAt < :today",
                        TouchpointDto.class)
                .setParameter("toMemberId", toMemberId)
                .setParameter("today", today.atStartOfDay())
                .getResultList();
    }

}