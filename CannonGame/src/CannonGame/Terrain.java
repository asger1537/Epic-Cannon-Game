package CannonGame;
import java.util.LinkedList; 
import java.util.Queue; 
import processing.core.PVector;
import processing.core.*;

public class Terrain {

int width = applet.width;
int offset = applet.height/4; 

Terrain(int width) {
	this.width = width;
	this.height = height;
	heightmap = new ArrayList<width>();
}	

void generate()
{
Queue<Integer> q = new LinkedList<>;

q.add(0);
q.add(width-1);
while(!q.isEmpty())
	{
		int x0 = q.remove();
		int x1 = q.remove();
		int mid = (x0 + x1)/2;
		heightmap[mid] = (heightmap[x0] + heightmap[x1])/2+random(-offset,offset);
		if(x1-mid > 1)
		{
			q.add(x0);
			q.add(mid);
			q.add(mid);
			q.add(x1);
		}
	}
}

{
for(int i = 0; i < tWidth-1; i++)
	{
	applet.line(i,applet.height,i,heightmap[i]);
	applet.line(i, heightmap[i],i+1, heightmap[i+1]);	
	}
}
boolean checkCollision(SquareBall squareball)
//todo 
}
