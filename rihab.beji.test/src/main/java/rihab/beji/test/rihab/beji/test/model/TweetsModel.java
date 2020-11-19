package rihab.beji.test.rihab.beji.test.model;

import lombok.Data;

@Data
public class TweetsModel {

	public TweetsModel(String tweet) {
		super();
		this.tweet = tweet;
	}

	String tweet;

	@Override
	public String toString() {
		return "" + tweet + "";
	}

}
