import java.awt.BufferedImage;

public class Tile{
	
	private BufferedImage tile, background;
	private String type, description;
	
	public Tile(BufferedImage t, BufferedImage b, String s, String d){
		tile=t;
		background=b;
		type=s;
	}
	
	public String getType(){
		return type;
	}
	
	public String getDesc(){
		return description;
	}
	
	public BufferedImage getTile(){
		return tile;
	}
	
	public BufferedImage getBackground(){
		return background;
	}
	
}
