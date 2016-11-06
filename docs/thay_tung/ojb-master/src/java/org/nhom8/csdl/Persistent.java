package org.nhom8.csdl;

public abstract class Persistent {
	private static ObjectID ID;
	
	public Persistent Object() {
		return this;
	}
	
	public static ObjectID getObject(){
		return ID;
	}
	public Object read() {
		return this;
	};
	public void write() {
		
	}
	public boolean store() {
		return false;
	}
	public boolean update() {
		return false;
	}
	public  void delete() {
	} 
	public Object find() {
		return this;
	}
	public Object iterate() {
		return this;
	}
	
}
