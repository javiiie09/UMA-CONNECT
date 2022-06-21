package ES.UMA.UMACONNECT.UMACONNECT.UTIL;

import java.util.List;

import ES.UMA.UMACONNECT.UMACONNECT.ENTITY.User;

public interface IRecomendador {
	public void recomendar(User user);
	public List<User> getRecomend();
}
