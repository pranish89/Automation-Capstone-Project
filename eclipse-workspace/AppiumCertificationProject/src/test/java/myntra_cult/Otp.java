package myntra_cult;

public class Otp {
	

		public static String verify_otp(String fullmsg)
		{
			System.out.println("removing all Alpha from message");
			String parser = fullmsg.replaceAll("[^0-9]", "");
			System.out.println("After Parsing " +parser);
			String otp = parser.substring(0,6);
			System.out.println("Otp " +otp);
			return otp;
			
			//String otp = fullmsg.split("is ")[1].trim();//--spliting based on words and taking the second part [1]
		
	}
	

}
