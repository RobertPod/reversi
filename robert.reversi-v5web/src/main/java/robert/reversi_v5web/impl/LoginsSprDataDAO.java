package robert.reversi_v5web.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsSprDataDAO extends CrudRepository<LoginsSprDataImpl, Long> {
	List<LoginsSprDataImpl> findBySession(String session);

	<S extends LoginsSprDataImpl> S save(S arg0);

}
