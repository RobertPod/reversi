package robert.reversi_v5web.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

public class SprDataUserDAOMethods {
	@Autowired
	protected SprDataUserDAO userDaoSpr;

	public UserSprDataImpl searchFirstUserByEmail(String email) {

		List<UserSprDataImpl> searchResList = userDaoSpr.findByEmail(email);
		return (CollectionUtils.isEmpty(searchResList) ? null : searchResList.get(0));
	}
}
