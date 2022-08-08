package school.hei.schoolapi.repository;

import school.hei.schoolapi.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JPQLImplementation implements StudentSimpleRepository{
    EntityManager em;
    @Override
    public List<Student> findAll() {
        Query query = em.createNativeQuery("SELECT * FROM student");
        List<Student> result = query.getResultList();
        return result;
    }

    @Override
    public Student save(Student s) {
        Query query = em.createNativeQuery("INSERT INTO student(name,group) VALUES (?,?)");
        query.setParameter(1,s.getName());
        query.setParameter(2,s.getGroup().getId());

        Query returnQuery = em.createNativeQuery("SELECT FROM student WHERE name = ? AND group_id = ?");
        query.setParameter(1,s.getName());
        query.setParameter(2,s.getGroup().getId());
        return (Student) query.getSingleResult();
    }

    @Override
    public Optional<Student> findById(Long id) {
        Query query = em.createNativeQuery("SELECT * FROM student WHERE id = ?");
        query.setParameter(1,id);
        return Optional.of((Student) query.getSingleResult());
    }

    @Override
    public void deleteById(Long Id) {
        Query query = em.createNativeQuery("DELETE FROM student WHERE id = ?");
        query.setParameter(1,Id);
    }

    @Override
    public List<Student> findWhereNameLike(String query) {
        Query q = em.createNativeQuery("SELECT * FROM student WHERE name LIKE ?");
        q.setParameter(1,"%"+query+"%");
        return q.getResultList();
    }
}
