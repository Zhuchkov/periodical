package periodical.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {
    Optional<E> find(int id);
    List<E> findAll();
    E insert(E e);
    E update(E e);
    E delete(int id);
}

