
public class newQuestionc {
	
	public static String newQuestion(int level){
		int ints[] = new int[level];
		
		for (int i = 0 ; i < level ; i++){
			ints [i] = (int) (Math.random () * 10);
		}
		
		 StringBuffer sbfNumbers = new StringBuffer();
         
        
         if(ints.length > 0){
        	 sbfNumbers.append(ints[0]);
                
                 /*
                  * Loop through the elements of an int array. Please
                  * note that loop starts from 1 not from 0 because we
                  * already appended the first element without leading space.s  
                  */
                 for(int i=1; i < ints.length; i++){
                         sbfNumbers.append(ints[i]);
                 }
                
         }
      
		String finalst = sbfNumbers.toString();
		System.out.println(finalst);
		return finalst;
	}
}
