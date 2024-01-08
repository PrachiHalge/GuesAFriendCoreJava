import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		GetDataServiceImpl getDataServiceImpl=new GetDataServiceImpl();
		ArrayList<SecretSanta> listGivers= getDataServiceImpl.getNamesWithNoToFriend();
	    
		System.out.println("Enter Your First name without space:-");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    String str;
		try {
			str = br.readLine();
			String toFriend=null;
			toFriend=getDataServiceImpl.getFriend(str.toLowerCase());
	    if(!(toFriend==null)){
	    	System.out.println("Please bring secret gift for your Friend:-"+toFriend);
	    }else{	    	
	    for (int i=0;i<listGivers.size();i++ ){
	    	if(listGivers.get(i).getFromName().toLowerCase().equals(str.toLowerCase())){
	    		if(!(getDataServiceImpl.getNamesWithNoFromFriend().isEmpty())){
	    		SecretSanta newFriend=getDataServiceImpl.getRandomElement(getDataServiceImpl.getNamesWithNoFromFriend(),listGivers.get(i).getFromName().toLowerCase());
	    		if(!(newFriend.equals(null))){
	    			Boolean updated=getDataServiceImpl.updateToName(listGivers.get(i).getFromName().toLowerCase(),newFriend.getFromName().toLowerCase());
	    			if(updated){
	    				System.out.println("Please bring secret gift for your Friend:"+newFriend.getFromName());
	    			}
	    	     }
	    	    }
	    }
	   }
	   }
	 } catch (IOException e) {			
		e.printStackTrace();
	}
	
	}
}
	 
	 



