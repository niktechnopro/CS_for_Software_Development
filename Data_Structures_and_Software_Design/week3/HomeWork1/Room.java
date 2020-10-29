
public class Room extends MapSite{//might be abstract class
	
	//fields
	public int roomNumber;
    public Wall sides;
    public MapSite[] mapsites;
	
	public Room() {//constructor
		
	}
	
	public Wall getSide(int n) {
		
		return sides;//we need to return Wall
	}
	
	public void setSide(int n, Wall w) {
		
	}

	@Override
	public void enter() {//technically we should implement this method since we exptend abstract class MapSite
		// TODO Auto-generated method stub
		
	}
	
}
