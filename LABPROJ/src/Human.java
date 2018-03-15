
public class Human {
	int x;
	int y;
	double size;
	int state; // 1 SUSCEPTIBLE// 2 IMMUNE// 3 DEAD
	
	public Human (int X, int Y, String STATE){
		
		x = X;
		y = Y;
		if (STATE == "susceptible")
			state = 1;
		if (STATE == "immune")
			state = 2;
		if (STATE == "dead")
			state = 3;
		size = 10;
		
		
		
	}
	

}
