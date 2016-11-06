package com.cloud.test;

import java.util.List;

import org.apache.ojb.odmg.OJB;
import org.odmg.Database;
import org.odmg.Implementation;
import org.odmg.ODMGException;
import org.odmg.OQLQuery;

import com.cloud.admin.model.Product;

import junit.framework.TestCase;

public class ServiceTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("===========setup=============");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("===========teardown=============");
	}
	
	public void testDb() {
		Implementation odmg = OJB.getInstance();
		Database db = odmg.newDatabase();
		try {
			db.open("default", Database.OPEN_READ_WRITE);
			//storeNewProduct(new Product("test", 10, 100.5f), db);
			assertNotNull(db);
			org.odmg.Transaction tx = odmg.newTransaction();
		    tx.begin();
		    // get current used Database instance
		   
		    // make persistent new object
				db.makePersistent(new Product("test", 10, 10.0));
			 tx.commit();
			db.close();
		} catch (ODMGException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		/* ... use the database ... */

		
	}
	 

	public static void storeNewProduct(Product product, Database db)
	{
	    // get the used Implementation instance
	    Implementation odmg = OJB.getInstance();
	    org.odmg.Transaction tx = odmg.newTransaction();
	    tx.begin();
	    // get current used Database instance
	   
	    // make persistent new object
	    db.makePersistent(product);
	    tx.commit();
	    
	}
	
	public static Product findProductByName(String name) throws Exception
	{
	    // get the used Implementation instance
		Implementation odmg = OJB.getInstance();
	    org.odmg.Transaction tx = odmg.newTransaction();
	    tx.begin();

	    OQLQuery query = odmg.newOQLQuery();
	    query.create("select products from "
	                 + Product.class.getName()
	                 + " where name = $1");
	    query.bind(name);
	    List<Product> results = (List) query.execute();
	    Product product = (Product) results.iterator().next();

	    tx.commit();
	    return product;
	}
}
