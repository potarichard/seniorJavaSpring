package hu.ponte.hr.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.ponte.hr.controller.ImageMeta;

@Service
public class SignService {

	@Value("${private_key}")
	String private_key_path;
	
	public void sign(ImageMeta img)
	{				
		try 
		{
			PrivateKey pk = getPrivateKeyFromFile(private_key_path);		
			byte[] data = img.getData();
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initSign(pk);
			signature.update(data);
			byte[] signedData = signature.sign();
			
			String encoded_sign = Base64.getEncoder().encodeToString(signedData);
			
			img.setDigitalSign(encoded_sign);
		} 
		
		catch (Exception e) {e.printStackTrace();}
		
	}
	
	
	 private static PrivateKey getPrivateKeyFromFile(String keyFile) throws Exception
	 {		    
		    byte[] der = Files.readAllBytes(Paths.get(keyFile));
		    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		    PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(der);
		    RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(ks);
		    return privKey;
	 }
	
}


