package assignment5;

import helper.Iter;

import java.util.HashMap;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

import meshes.Face;
import meshes.HalfEdgeStructure;
import meshes.Vertex;
import openGL.objects.Transformation;


/** 
 * Implement the QSlim algorithm here
 * 
 * @author Alf
 *
 */
public class QSlim {
	
	private HalfEdgeStructure hs;
	
	private HashMap<Vertex, Matrix4f> errorQuadrics;
	
	/********************************************
	 * Use or discard the skeleton, as you like.
	 ********************************************/
	
	public QSlim(HalfEdgeStructure hs) {
		this.hs = hs;
		this.init();
	}
	
	
	/**
	 * Compute per vertex matrices
	 * Compute edge collapse costs,
	 * Fill up the Priority queue/heap or similar
	 */
	private void init(){
		this.errorQuadrics = new HashMap<Vertex, Matrix4f>();
		
		for(Vertex v: hs.getVertices())
		{
			Matrix4f out = new Matrix4f();
			for(Face f: Iter.ate(v.iteratorVF()))
			{
				float[] planeArray = new float[4];
				f.plane().get(planeArray);
				Matrix4f errorQuadric = new Matrix4f();
				for(int i = 0; i < 4; i++)
				{
					for(int j = 0; j < 4; j++)
					{
						errorQuadric.setElement(i, j, planeArray[i] * planeArray[j]);
					}
				}
				out.add(errorQuadric);
			}
			this.errorQuadrics.put(v, out);
		}
	}
	
	
	/**
	 * The actual QSlim algorithm, collapse edges until
	 * the target number of vertices is reached.
	 * @param target
	 */
	public void simplify(int target){
		
	}
	
	
	/**
	 * Collapse the next cheapest eligible edge. ; this method can be called
	 * until some target number of vertices is reached.
	 */
	public void collapsEdge(){
		
	}
	
	public Matrix4f getErrorQuadric(Vertex v)
	{
		return errorQuadrics.get(v);
	}
	
	/**
	 * helper method that might be useful..
	 * @param p
	 * @param ppT
	 */
	private void compute_ppT(Vector4f p, Transformation ppT) {
		assert(p.x*0==0);
		assert(p.y*0==0);
		assert(p.z*0==0);
		assert(p.w*0==0);
		ppT.m00 = p.x*p.x; ppT.m01 = p.x*p.y; ppT.m02 = p.x*p.z; ppT.m03 = p.x*p.w;
		ppT.m10 = p.y*p.x; ppT.m11 = p.y*p.y; ppT.m12 = p.y*p.z; ppT.m13 = p.y*p.w;
		ppT.m20 = p.z*p.x; ppT.m21 = p.z*p.y; ppT.m22 = p.z*p.z; ppT.m23 = p.z*p.w;
		ppT.m30 = p.w*p.x; ppT.m31 = p.w*p.y; ppT.m32 = p.w*p.z; ppT.m33 = p.w*p.w;
			
		
	}
	
	
	
	
	
	/**
	 * Represent a potential collapse
	 * @author Alf
	 *
	 */
	protected class PotentialCollapse implements Comparable<PotentialCollapse>{

		@Override
		public int compareTo(PotentialCollapse arg1) {
			return -1;
		}
	}

}
