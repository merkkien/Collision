import java.awt.List;
import java.util.ArrayList;

import remixlab.proscene.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;
import remixlab.dandelion.constraint.*;

public class collision
    {
     	static ArrayList<CollisionSegment> colitionSegments = new ArrayList<CollisionSegment>();
        static int updateFreq = 10;
        static int counter;
        static boolean ghostMode;
        
        public static boolean isGhostMode() {
			return ghostMode;
		}

		public static void setGhostMode(boolean ghostMode) {
			collision.ghostMode = ghostMode;
		}

		static int id;

        public static int GenerateCollitionId()
        {
            return (id++);
        }

        public static void AddCollisionSegment(Vec first, Vec second, float colitionDistance)
        {
            Segment cSegment = new Segment(first, second);
            CollisionSegment cCollisionSegment = new CollisionSegment();
            cCollisionSegment.segment = cSegment;
            cCollisionSegment.setColitionDistance(colitionDistance);
            cCollisionSegment.setEnabled(true);   
            colitionSegments.add(cCollisionSegment);
        }


        public static boolean CheckCollision(Vec camaraPos)
        {
            if (!ghostMode) //if colitions are not disabled
            {
            	for (CollisionSegment item : colitionSegments) {
				
                    if (item.segment.DistToSegment(camaraPos) < item.getColitionDistance())
                    {
                        return true; 
                    }
                }
            }
            return false; 
        }

    }
