package robert.reversi_v5web.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsSprDataDAO extends CrudRepository<LoginsSprDataImpl, Long> {
	List<LoginsSprDataImpl> findBySession(String session);

	List<LoginsSprDataImpl> findByLogoutTimeGreaterThan(long logoutTime);

	List<LoginsSprDataImpl> findByLogoutTimeGreaterThanOrderByLogoutTimeDesc(long logoutTime);

	List<LoginsSprDataImpl> findFirst20ByLogoutTimeGreaterThanOrderByLogoutTimeDesc(long logoutTime);

	List<LoginsSprDataImpl> findFirst80ByLogoutTimeGreaterThanOrderByLogoutTimeDesc(long logoutTime);

	<S extends LoginsSprDataImpl> S save(S arg0);

}
