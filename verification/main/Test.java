package main;



public class Test {
	
	public static void main(String[] args) {
		
		Authority authority = new AuthorityImpl();
		
		authority.postKey(String.valueOf(1000));
		authority.postKey(String.valueOf(999));
		
		String first = authority.getPrivateKey(String.valueOf(1000));
		String second = authority.getPrivateKey(String.valueOf(999));
		String third = authority.getPublicKey(String.valueOf(1000));
		System.out.println(first);
		System.out.println(third);
		System.out.println(first.equals(second));
		System.out.println(first.equals(third));
		
		
		
		/*DataBaseManager dbm=new DataBaseManager();
		dbm.getVoteFromDataBase("1");
		System.out.println(dbm.getPrivateKey("1"));*/
	}
}