package uriparser;  // DO NOT CHANGE THIS OR YOU WILL GET ZERO

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
	 * Test the parse method to make sure it throws a @see uriparser.ParseException
	 * when null is the input to the @see uriparser.URIParser#parse(java.lang.String);
	 */
	@Test
	public void testNull() {
		try {
			uri = parser.parse(null);
			fail("Doesn't throw ParseException");
		} catch(ParseException e) {}
	}


	/**
	 * 
	 */
	@Test
	public void testSpace() {
		try {
			uri = parser.parse(" ");
			fail("Doesn't throw ParseException");
		} catch(ParseException e) {}
	}


	/**
	 * Test a URI with non empty strings for all parts of the URI.
	 */
	@Test
	public void testNormal() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertEquals("INCORRECT SCHEME", "http", uri.getScheme());
		assertEquals("INCORRECT AUTHORITY", "www.cs.auckland.ac.nz", uri.getAuthority());
		assertEquals("INCORRECT PATH", "/news/index.php", uri.getPath());
		assertEquals("INCORRECT QUERY", "item=199", uri.getQuery());
		assertEquals("INCORRECT FRAGMENT", "testFrag", uri.getFragment());
	}


	/**
	 * Test an empty string URI, where all parts of the URI should
	 * then be null.
	 */
	@Test
	public void testEmptyString() {
		uri = parser.parse("");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyAll() {
		uri = parser.parse("://?#");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("://", uri.getPath());
		assertEquals("", uri.getQuery());
		assertEquals("", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testSchemeOnly() {
		uri = parser.parse("http:");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testAuthorityOnly() {
		uri = parser.parse("//www.cs.auckland.ac.nz");
		assertNull(uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testPathOnly() {
		uri = parser.parse("/news/index.php");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testQueryOnly() {
		uri = parser.parse("?item=199");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testFragmentOnly() {
		uri = parser.parse("#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	


	/**
	 * Test a string with no scheme. This should **************************  
	 */
	@Test
	public void testNoScheme() {
		uri = parser.parse("//www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoSchemeNoAuthority() {
		uri = parser.parse("/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoSchemeNoPath() {
		uri = parser.parse("//www.cs.auckland.ac.nz?item=199#testFrag");
		assertNull(uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoSchemeNoQuery() {
		uri = parser.parse("//www.cs.auckland.ac.nz/news/index.php#testFrag");
		assertNull(uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoSchemeNoFragment() {
		uri = parser.parse("//www.cs.auckland.ac.nz/news/index.php?item=199");
		assertNull(uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}
	

	/**
	 * Test 
	 */
	@Test
	public void testNoAuthority() {
		uri = parser.parse("http:/news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * Test 
	 */
	@Test
	public void testNoAuthorityNoPath() {
		uri = parser.parse("http:?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * Test 
	 */
	@Test
	public void testNoAuthorityNoQuery() {
		uri = parser.parse("http:/news/index.php#testFrag");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * Test 
	 */
	@Test
	public void testNoAuthorityNoFragment() {
		uri = parser.parse("http:/news/index.php?item=199");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testNoPath() {
		uri = parser.parse("http://www.cs.auckland.ac.nz?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoPathNoQuery() {
		uri = parser.parse("http://www.cs.auckland.ac.nz#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoPathNoFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz?item=199");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testNoQuery() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testNoQueryNoFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testNoFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?item=199");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * Test a string with an empty scheme. This should **************************  
	 */
	@Test
	public void testEmptyScheme() {
		uri = parser.parse("://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("://www.cs.auckland.ac.nz/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyAuthority() {
		uri = parser.parse("http:///news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	// Empty path = no path


	/**
	 * 
	 */
	@Test
	public void testEmptyQuery() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?item=199#");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyAuthorityAndPath() {
		uri = parser.parse("http://?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyAuthorityAndQuery() {
		uri = parser.parse("http:///news/index.php?#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyAuthorityAndFragment() {
		uri = parser.parse("http:///news/index.php?item=199#");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testEmptyQueryAndFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php?#");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority()); 
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("", uri.getQuery());
		assertEquals("", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testNoAuthorityPathWithNoSlash() {
		uri = parser.parse("http:news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority()); 
		assertEquals("news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test 
	public void testNoQueryAndFragment() {
		uri = parser.parse("http://www.cs.auckland.ac.nz/news/index.php");
		assertEquals("http", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeOneCharacter() {
		uri = parser.parse("h://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertEquals("h", uri.getScheme());
		assertEquals("www.cs.auckland.ac.nz", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeWithSlash() {
		uri = parser.parse("/://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertEquals("/://www.cs.auckland.ac.nz/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeWithQuestion() {
		uri = parser.parse("?://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("://www.cs.auckland.ac.nz/news/index.php?item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeWithHash() {
		uri = parser.parse("#://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("://www.cs.auckland.ac.nz/news/index.php?item=199#testFrag", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testAuthorityWithColon() {
		uri = parser.parse("http://:/news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals(":", uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testAuthorityWithQuestion() {
		uri = parser.parse("http://?/news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("/news/index.php?item=199", uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	/**
	 * T
	 */
	@Test
	public void testAuthorityWithHash() {
		uri = parser.parse("http://#/news/index.php?item=199#testFrag");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("/news/index.php?item=199#testFrag", uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testColon() {
		uri = parser.parse(":");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertEquals(":", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSlash() {
		uri = parser.parse("/");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertEquals("/", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testQuestion() {
		uri = parser.parse("?");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("", uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testHash() {
		uri = parser.parse("#");
		assertNull(uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testSchemeAndEmptyAuthority() {
		uri = parser.parse("http://");
		assertEquals("http", uri.getScheme());
		assertEquals("", uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * www.cs.auckland.ac.nz/news/index.php?item=199#testFrag
	 */
	@Test
	public void testSchemeAndAuthority() {
		uri = parser.parse("http://www.www.cs.auckland.ac.nz");
		assertEquals("http", uri.getScheme());
		assertEquals("www.www.cs.auckland.ac.nz", uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeAndEmptyQuery() {
		uri = parser.parse("http:?");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("", uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testSchemeAndQuery() {
		uri = parser.parse("http:?item=199");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertEquals("item=199", uri.getQuery());
		assertNull(uri.getFragment());
	}


	/**
	 * 
	 */
	@Test
	public void testSchemeAndEmptyFragment() {
		uri = parser.parse("http:#");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("", uri.getFragment());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testSchemeAndFragment() {
		uri = parser.parse("http:#testFrag");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertNull(uri.getPath());
		assertNull(uri.getQuery());
		assertEquals("testFrag", uri.getFragment());
	}
	
	
	
	
	/**
	 * 
	 */
	@Test
	public void testSchemeAndPath() {
		uri = parser.parse("http:/news/index.php");
		assertEquals("http", uri.getScheme());
		assertNull(uri.getAuthority());
		assertEquals("/news/index.php", uri.getPath());
		assertNull(uri.getQuery());
		assertNull(uri.getFragment());
	}
	
	
	




}

