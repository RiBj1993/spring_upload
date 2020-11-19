package rihab.beji.test.rihab.beji.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.Data;

public class UserIntegrationTest extends TestCase {

	public void givenAnnotatedUser_thenHasGettersAndSetters() {
		User user = new User();
		user.setFirstName("Test");
		assertEquals(user.getFirstName(), "Test");
	}

	public void testApp() {
		givenAnnotatedUser_thenHasGettersAndSetters();
	}

	public static Test suite() {
		return new TestSuite(UserIntegrationTest.class);
	}

	@Data
	class User {
		private String firstName;
	}
}