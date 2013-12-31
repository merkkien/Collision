import processing.core.*;
import processing.opengl.*;
import remixlab.proscene.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;
import remixlab.dandelion.constraint.*;

@SuppressWarnings("serial")
public class Escenario extends PApplet {
	Scene scene;
	PShape model;
	InteractiveAvatarFrame avatar;
	
	public void setup()
	{
		size(640, 512, P3D); 
		scene = new Scene(this);
		model = loadShape( "plaza.obj");
		model.scale(0.2f,- 0.2f, 0.2f); // invert model
		model.translate(0, 1.4f, 0);
		
		collision.AddCollisionSegment(new Vec(-14.4f, 28.3f, 0), new Vec(-14.5f, -11.6f, 0), 0.5f);
        collision.AddCollisionSegment(new Vec(-14.4f, -11.6f, 0), new Vec(20.1f, -11.7f, 0), 0.5f);
        
    	avatar = new InteractiveAvatarFrame(scene);
		avatar.setTrackingDistance(30);
		avatar.setAzimuth(PI/12);
		avatar.setInclination(PI/6);
		WorldConstraint baseConstraint = new WorldConstraint();
		baseConstraint.setTranslationConstraint(AxisPlaneConstraint.Type.PLANE, new Vec(0.0f, 1.0f, 0.0f));
		baseConstraint.setRotationConstraint(AxisPlaneConstraint.Type.AXIS, new Vec(0.0f, 1.0f, 0.0f));
		avatar.setConstraint(baseConstraint);
	}
	public void draw()
	{
	  background(32);
	  lights();
	  
	  if (collision.CheckCollision(new Vec(avatar.viewportPosition())))
      {
       print("COLISION");
      }
	  pushMatrix();
	  scene.applyTransformation(avatar);
	  translate(3f, -4f, 3f);
	  sphere(0.5f);	  
	  popMatrix();
	  
	  shape(model);
	}
	
	public void keyPressed() { 
		  if ( key == ' ' )
		    if ( scene.avatar() == null ) {
		      scene.setAvatar(avatar);
		      scene.defaultMouseAgent().setAsThirdPerson();
		      scene.defaultMouseAgent().setDefaultGrabber(avatar);
		      scene.defaultMouseAgent().disableTracking();
		    }
		    else {
		      scene.unsetAvatar(); //simply sets avatar as null
		      scene.defaultMouseAgent().setAsArcball();
		      scene.defaultMouseAgent().setDefaultGrabber(scene.viewport().frame());
		      scene.defaultMouseAgent().enableTracking();
		    }
		}
}


