package robert.reversi_v5web.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprDataUserDAO extends CrudRepository<UserSprDataImpl, Long> {
	UserSprDataImpl findByUserId(Long userId);

	List<UserSprDataImpl> findByEmail(String email);

	List<UserSprDataImpl> findByEmailLike(String email);

	List<UserSprDataImpl> findByEmailLikeOrderByUserId(String email);

	<S extends UserSprDataImpl> S save(S arg0);
}
