package CannonGame;
import java.util.LinkedList; 
import java.util.Queue; 
import static CannonGame.CannonGame.applet;

public class Terrain{
int tHeight;
int tWidth;
int offset; 
int[] heightmap;

Terrain()
{
	this.tWidth = applet.width;
	this.tHeight = applet.height;
	this.offset = applet.height/4;
}	

void generate()
{
	int[] heightmap = new int[tWidth]; 
	heightmap[0]= (int)applet.random(tHeight);
	heightmap[tWidth-1]= (int)applet.random(tHeight);
	Queue<Integer> q = new LinkedList<>();

q.add(0);
q.add(tWidth-1);
while(!q.isEmpty())
	{
		int x0 = q.remove();
		int x1 = q.remove();
		int mid = (x0 + x1)/2;
		heightmap[mid] =(int)((heightmap[x0]+heightmap[x1])/2+applet.random(-offset,offset));
		if(x1-mid > 1)
		{
			q.add(x0);
			q.add(mid);

			q.add(mid);
			q.add(x1);
		}
	}
}

void displayTerrain()
{
for(int i = 0; i < tWidth-1; i++)
	{
	applet.line(i,applet.height,i,heightmap[i]);
	applet.line(i, heightmap[i],i+1, heightmap[i+1]);	
	}
}
}
