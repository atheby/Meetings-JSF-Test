package main.java.com.atheby.meetings.manager;

import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.*;
import java.security.spec.*;

public class PasswordManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**	
	 * @author Jerry Orr
	 * @see <a href="http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html">http://www.javacodegeeks.com</a>
	 */
	public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

		// Encrypt the clear-text password using the same salt that was used to
		// encrypt the original password
		byte[] encryptedAttemptedPassword = encryptPassword(attemptedPassword, salt);
		
		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}
	
	/**	
	 * @author Jerry Orr
	 * @see <a href="http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html">http://www.javacodegeeks.com</a>
	 */
	public byte[] encryptPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
		// specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		String algorithm = "PBKDF2WithHmacSHA1";
		// SHA-1 generates 160 bit hashes, so that's what makes sense here
		int derivedKeyLength = 160;
		
		// Pick an iteration count that works for you. The NIST recommends at
		// least 1,000 iterations:
		// http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		// iOS 4.x reportedly uses 10,000:
		int iterations = 20000;
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		
		return f.generateSecret(spec).getEncoded();
	}
	
	/**	
	 * @author Jerry Orr
	 * @see <a href="http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html">http://www.javacodegeeks.com</a>
	 */
	public byte[] generateSalt() throws NoSuchAlgorithmException {
		
		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		
		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}
}
