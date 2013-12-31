import remixlab.proscene.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;
import remixlab.dandelion.constraint.*;

public class Segment {
	public Vec first;
    public Vec second;
    float angleSegment;
    float m1, m2;
    float n1;

    public Segment(Vec first, Vec second)
    {
        this.first = first;
        this.second = second;

        //avoid that the M is 0 or undefined
        if (first.y() == second.y())
        {
            this.first.set(first.x(),first.y() + 0.001f,0f);
        }
        if (second.x() == first.x())
        {
        	this.second.set(second.x() + 0.001f,second.y(),0f);
        }
        //calculate the pendient of the function a the perpendicular
        //pendient
        m1 = (this.second.y() - this.first.y()) / (this.second.x() - this.first.x());
        m2 = -1 / m1;
        //calculate the y intersection
        n1 = this.first.x() - m1 * this.first.x();
        //calculate the angle with the x axis
        this.angleSegment = (float) Math.toDegrees(Math.atan((float)m1));
    }
    
    public float DistToSegment(Vec other)
    {
    	float x;
        //calculate the distances to each point
        float dist1 = Vec.dist(new Vec(other.x(), other.y(), 0), new Vec(first.x(), first.y(), 0));
        float dist2 = Vec.dist(new Vec(other.x(), other.y(), 0), new Vec(second.x(), second.y(), 0));

        //calculate the two linear functions
        float n2 = other.y() - m2 * other.x();   //y = m2x + n   m2 = -1/m1

        //calculate the intersection point(i.p.)
        Vec intersectionPoint = new Vec();
        x = (n2 - n1) / (m1 - m2);
        intersectionPoint.set(x,m1 * x + n1, 0);
        
        float d = Vec.dist(intersectionPoint, other);

        //if the i.p. is contained in the rect segment return d else 
        //the minimun value between dist1 and dist2
        if ((intersectionPoint.x() <= first.x() && intersectionPoint.x() >= second.x()) ||
            (intersectionPoint.x() >= first.x() && intersectionPoint.x() <= second.x()))
        {
            return d;
        }
        else
            return 100;//Math.Min(dist1, dist2);
    }
}
