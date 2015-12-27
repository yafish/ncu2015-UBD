package dom;

import java.util.HashMap;
import java.util.Vector;

import gameobject.Item;
import gameobject.Player;

public class DOM {
	private Player player[];
	private HashMap<Integer, Item> items;
	private int mapType;
	private int gameTime;
	private int clientPlayerID;
	public DOM(){
		player = new Player[4];
		items = new HashMap<Integer, Item>();
	}
	
	public void updatePlayer(Player player){
		this.player[player.getID()] = player;
	}
	
	public Player getPlayer(int playerID){
		return player[playerID];
	}
	
	public void setMapType(int mapType){
		this.mapType = mapType;
	}
	
	public int getMapType(){
		return mapType;
	}
	
	public void setGameTime(int gameTime){
		this.gameTime = gameTime;
	}
	
	public int getGameTime(){
		return this.gameTime;
	}
	
	public void addItem(Item item){
		items.put(item.getID(), item);
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	
	public Item getItem(int itemID){
		return items.get(itemID);
	}
	
	public HashMap<Integer, Item> getItems(){
		return items;
	}
	
	public void setClientPlayerID(int playerID){
		this.clientPlayerID = playerID;
	}
	
	public int getClientPlayerID(){
		return clientPlayerID;
	}
	
	public Vector<String> getAllDOM(){
		Vector<String> temp = new Vector<String>();
		for(Player t : player)
			temp.add(t.toString());
		items.forEach((k,v)->temp.add(v.toString()));
		temp.add("gameTime,"+gameTime);
		return temp;
	}
	
}
