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
	// left terrain point
	heightmap[0]= (int)applet.random(tHeight);
	// right terrain point 
	heightmap[tWidth-1]= (int)applet.random(tHeight);
	// make a queue
	Queue<Integer> q = new LinkedList<>();

// add first segment (left and right terrain point)	
q.add(0);
q.add(tWidth-1);
//generates terrain until distance between points is not divisable by 2
while(!q.isEmpty())
	{
		// set x0 to 0 and remove 0 from queue
		int x0 = q.remove();
		// set x1 to tWidth-1 and remove it from the queue
		int x1 = q.remove();
		// the mid point (x) is avearge of the two points
		int mid = (x0 + x1)/2;
		// the y coord for mid point is avearge of left and right point +- offset
		heightmap[mid] =(int)((heightmap[x0]+heightmap[x1])/2+applet.random(-offset,offset));
		//continue if divisable by 2
		if(x1-mid > 1)
		{
			// add the next 2 segments made of the middle point and the left/right terrain point
			q.add(x0);
			q.add(mid);
			
			q.add(mid);
			q.add(x1);
		}
	}
}

void displayTerrain()
{
	//loop through length of array
for(int i = 0; i < tWidth-1; i++)
	{
	//the vertical line from the botoom of the screen to the current terrain point
	applet.line(i,applet.height,i,heightmap[i]);
	//the line from current terrain point to the next terrain point
	applet.line(i, heightmap[i],i+1, heightmap[i+1]);	
	}
}
}
