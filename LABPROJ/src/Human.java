
public class Human {
	
	private int x;
	private int y;
	private int size;
	private int state; // 1 SUSCEPTIBLE// 2 IMMUNE// 3 DEAD
	
	public Human (int X, int Y, String STATE){
		
		x = X;
		y = Y;
		if (STATE == "susceptible")
			state = 1;
		if (STATE == "immune")
			state = 2;
		if (STATE == "dead")
			state = 3;
		size = 20;
		
		
		
	}
	
	public int getX ()
	{
		return this.x;
	}
	
	
	
	public int getY ()
	{
		return this.y;
	}

	public int getSize ()
	{
		return this.size;
	}

	public int getState ()
	{
		return this.state;
	}
	
	public void setState (int STATE)
	{
		state = STATE;
	}
	
}
