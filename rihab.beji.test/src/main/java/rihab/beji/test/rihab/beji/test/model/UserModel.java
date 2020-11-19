package rihab.beji.test.rihab.beji.test.model;

import java.util.List;

import lombok.Data;

@Data
public class UserModel {

	private String name_of_user;
	private List<TweetsModel> tweets_of_user;
	private List<UserModel> followers_of_user;

	@Override
	public String toString() {
		StringBuilder S = new StringBuilder("");

		for (UserModel follow : followers_of_user) {

			if (follow != null) {
				for (TweetsModel tweet : follow.getTweets_of_user()) {

					S.append("\t@" + follow.getName_of_user());

					if (tweet != null) {
						S.append(":" + tweet.getTweet() + "\n\t ");
					}
				}
			}
		}

		for (TweetsModel tweet : tweets_of_user) {

			S.append("\t@" + name_of_user);

			if (tweet != null) {
				S.append(":" + tweet.getTweet() + "\n\t");
			}
		}

		String val = ("\n\t" + name_of_user + "\n\t  \n\t" + "" + S.toString() + "");

		return val.substring(1, val.length() - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (followers_of_user == null) {
			if (other.followers_of_user != null)
				return false;
		} else if (!followers_of_user.equals(other.followers_of_user))
			return false;
		if (name_of_user == null) {
			if (other.name_of_user != null)
				return false;
		} else if (!name_of_user.equals(other.name_of_user))
			return false;
		if (tweets_of_user == null) {
			if (other.tweets_of_user != null)
				return false;
		} else if (!tweets_of_user.equals(other.tweets_of_user))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name_of_user == null) ? 0 : name_of_user.hashCode());

		return result;
	}

}
