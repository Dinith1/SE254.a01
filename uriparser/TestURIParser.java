package uriparser;  // DO NOT CHANGE THIS OR YOU WILL GET ZERO

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 * SOFTENG 254 2018 Assignment 1 submission
 *
 * Author: (Dinith Wannigama, dwan609)
 **/

public class TestURIParser {// DO NOT CHANGE THE CLASS NAME OR YOU WILL GET ZERO


	// ****************************** CORRECT WAY TO DO JAVADOC???????? ********************************



	URIParser parser;
	URI uri;

	@Before
	public void setUp() {
		parser = new URIParser();
	}


	/**
	 * Test the parse method to make sure it throws a @see uriparser.ParseExcetion
	 * when null is the input to the @see uriparser.URIParser#parse(java.lang.String);
	 */
	@Test
	public void testNull() {
		try {
			uri = parser.parse(null);
		} catch(ParseException e) {}
	}

	/**
	 * 
	 */
	@Test
	public void testSpace() {
		try {
			uri = parser.parse(" ");
		} catch(ParseException e) {}
	}


	/**
	 * Test a URI with non empty strings for all parts of the URI.
	 */
	@Test
	public void testNormal() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?item=199#memes123.lol");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("memes123.lol", uri.getFragment());
	}


	/**
	 * Test an empty string URI, where all parts of the URI should
	 * then be null.
	 */
	@Test
	public void testEmpty() {
		uri = parser.parse("");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * Test a string with an empty scheme. This should **************************  
	 */
	@Test
	public void testEmptyScheme() {
		uri = parser.parse("://www.cs.auckland.ac.nz/news/index.php?item=199#memes123.lol");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("://www.cs.auckland.ac.nz/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("memes123.lol", uri.getFragment());
	}
	
	
	/**
	 * Test
	 */
	@Test
	public void testEmptyAuthority() {
		
	}


	@Test
	public void testNoQueryOrFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
	}


	
}
