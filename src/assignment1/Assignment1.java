package assignment1;

import glWrapper.GLHalfEdgeStructure;
import glWrapper.VertexAttribute;

import java.io.IOException;
import java.util.Iterator;

import javax.vecmath.Vector3f;

import meshes.HalfEdge;
import meshes.HalfEdgeStructure;
import meshes.Vertex;
import meshes.WireframeMesh;
import meshes.exception.DanglingTriangleException;
import meshes.exception.MeshNotOrientedException;
import meshes.reader.ObjReader;
import openGL.MyDisplay;

/**
 * 
 * @author Alf
 *
 */
public class Assignment1 {

	public static void main(String[] args) throws IOException{
		//Load a wireframe mesh
		WireframeMesh m = ObjReader.read("./objs/bunny5k.obj", true);
		HalfEdgeStructure hs = new HalfEdgeStructure();
		
		//create a half-edge structure out of the wireframe description.
		//As not every mesh can be represented as a half-edge structure
		//exceptions could occur.
		try {
			hs.init(m);
		} catch (MeshNotOrientedException | DanglingTriangleException e) {
			e.printStackTrace();
			return;
		}
		
		

		//... do something with it, display it ....
		GLHalfEdgeStructure glMeshDiffuse = new GLHalfEdgeStructure(hs);
		glMeshDiffuse.configurePreferredShader("shaders/trimesh_flat.vert", 
				"shaders/trimesh_flat.frag", 
				"shaders/trimesh_flat.geom");
		
		
		//... do something with it, display it ....
		GLHalfEdgeStructure glMeshValence = new GLHalfEdgeStructure(hs);
		glMeshValence.addElement(1, "valence", new VertexAttribute() {
			@Override
			public float[] getAttribute(Vertex v) {
				return new float[]{v.getValence()};
			}
		});
		glMeshValence.configurePreferredShader("shaders/valence.vert", 
				"shaders/default.frag");
		
		GLHalfEdgeStructure glMeshNormal = new GLHalfEdgeStructure(hs);
		glMeshNormal.addElement(3, "normal", new VertexAttribute() {
			@Override
			public float[] getAttribute(Vertex v) {
				Vector3f normal = v.getNormal();
				return new float[]{normal.x, normal.y, normal.z};
			}
		});
		glMeshNormal.addElement(3, "color", new VertexAttribute() {
			
			@Override
			public float[] getAttribute(Vertex v) {
				return new float[]{.4f, .5f, .2f};
			}
		});
		glMeshNormal.configurePreferredShader("shaders/normal.vert", 
				"shaders/normal.frag");

		
		MyDisplay disp = new MyDisplay();
		/*disp.addToDisplay(glMeshDiffuse);
		disp.addToDisplay(glMeshValence);*/
		disp.addToDisplay(glMeshNormal);

		/*GLHalfEdgeStructure glMeshUnsmoothed = new GLHalfEdgeStructure(hs);
		glMeshUnsmoothed.configurePreferredShader("shaders/trimesh_flat.vert", 
				"shaders/trimesh_flat.frag", 
				"shaders/trimesh_flat.geom");
		disp.addToDisplay(glMeshUnsmoothed);
		AvgSmoother smoother = new AvgSmoother(hs);
		for(int i = 0; i < 40; i++)
		{
			smoother.apply();
		}
		GLHalfEdgeStructure glMeshSmoothed = new GLHalfEdgeStructure(hs);
		glMeshSmoothed.configurePreferredShader("shaders/trimesh_flat.vert", 
				"shaders/trimesh_flat.frag", 
				"shaders/trimesh_flat.geom");
		disp.addToDisplay(glMeshSmoothed);*/
		
		
		
		
		

		
		/*WireframeMesh oneNeighbourhood = ObjReader.read("./objs/oneNeighborhood.obj", true);
		HalfEdgeStructure oneNeighbourhoodHS = new HalfEdgeStructure();
		
		try {
			oneNeighbourhoodHS.init(oneNeighbourhood);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		oneNeighbourhoodHS.enumerateVertices();
		
		Vertex center = oneNeighbourhoodHS.getVertices().get(0);
		Iterator<HalfEdge> iteratorVE = center.iteratorVE();
		for(HalfEdge he = null; iteratorVE.hasNext(); )
		{
			he = iteratorVE.next();
			System.out.println(he);
		}
		Iterator<Vertex> iteratorVV = center.iteratorVV();
		for(Vertex he = null; iteratorVV.hasNext(); )
		{
			he = iteratorVV.next();
			System.out.println(he);
		}
		Iterator<Face> iteratorVF = center.iteratorVF();
		for(Face he = null; iteratorVF.hasNext(); )
		{
			he = iteratorVF.next();
			System.out.println(he);
		}*/
	}
	

}
