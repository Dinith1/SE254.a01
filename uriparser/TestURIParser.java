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
	/**
	 * This method is run before every test, so a new URIParser object is made before
	 * each test.
	 */
	@Before
	public void setUp() {
		parser = new URIParser();
	}


	/**
	 * Test the parse method to make sure it throws a @see uriparser.ParseException
	 * when null is the input to the @see uriparser.URIParser#parse(java.lang.String)
	 */
	@Test
	public void testNull() {
		try {
			uri = parser.parse(null);
			fail("Doesn't throw ParseException");
		} catch(ParseException e) {}
	}


	/**
	 * Test a URI with all parts of the URI present and not empty. This makes sure
	 * the parser can parse the most basic URI. 
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
	 * Test an empty string URI, to make sure all parts of the URI should then be null, 
	 * as none of the syntax for scheme, authority, query and fragment are 
	 * present, and it is not possible to have an empty path.
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
	 * Test a string with all of the URI syntax, but with each part being empty.
	 * It is not possible to have and empty scheme, as the colon character is 
	 * legal for paths, meaning anything after the colon that is legal for path (:, /)
	 * will count as the path.
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
	 * Test a string with only a scheme present. It should not be possible
	 * to produce an empty path (after the colon). All parts apart from scheme
	 * should be null.
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
	 * Test a string where only an authority is present. This again enforces
	 * the no-empty-path rule, and makes sure a null scheme can be produced
	 * with a non-null authority.
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
	 * Test a string with a path only. Makes sure that the URIParser class
	 * only produces a non-null authority when a string follows two forward
	 * slashes.
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
	 * Test a string with only a query. Makes sure that the URIParser class
	 * allows only a query to be produced, where all other parts of the URI
	 * are null.
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
	 * Test a string with only a fragment. Makes sure that the URIParser class
	 * allows only a fragment to be produced, where all other parts of the URI
	 * are null.
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
	 * Test a string with no scheme, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while scheme 
	 * is null.
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
	 * Test a string with no scheme nor authority, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while scheme and authority
	 * are null.
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
	 * Test a string with no scheme nor path, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while scheme and path
	 * are null.
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
	 * Test a string with no scheme nor query, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while scheme and query
	 * are null
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
	 * Test a string with no scheme nor fragment, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while scheme and fragment
	 * are null
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
	 * Test a string with no authority, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while authority 
	 * is null
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
	 * Test a string with no authority nor path, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while authority and path 
	 * are null
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
	 * Test a string with no authority nor query, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while authority and query 
	 * are null 
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
	 * Test a string with no authority nor fragment, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while authority and fragment
	 * are null
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
	 * Test a string with no path, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while path 
	 * is null
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
	 * Test a string with no path nor query, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while path and query
	 * are null
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
	 * Test a string with no path nor fragment, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while path and fragment
	 * are null
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
	 * Test a string with no query, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while query
	 * is null
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
	 * Test a string with no query nor fragment, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while query and fragment
	 * are null
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
	 * Test a string with no fragment, but all other parts present. Should still
	 * allow all other parts of the URI to be produced normally, while fragment
	 * is null
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
	 * Test a string with an empty scheme. This should produce a null scheme and
	 * authority, as : and / are legal characters for the path, and all other parts
	 * are not null. 
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
	 * Test a string with an empty authority. This should produce a URI object
	 * with no parts being null, as having an empty authority is legal.
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


	/**
	 * Test a string with an empty query. This should produce a URI object
	 * with no parts being null, as having an empty query is legal.
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
	 * Test a string with an empty fragment. This should produce a URI object
	 * with no parts being null, as having an empty fragment is legal.
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
	 * Test a string with an empty authority and path. This should produce a URI object
	 * with only path being null, as having an empty path is not possible.
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
	 * Test a string with an empty authority and query. This should produce a URI object
	 * with no parts being null, as having an empty authority and query is legal.
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
	 * Test a string with an empty authority and fragment. This should produce a URI object
	 * with no parts being null, as having an empty authority and fragment is legal.
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
	 * Test a string with an empty query and fragment. This should produce a URI object
	 * with no parts being null, as having an empty query and fragment is legal.
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
	 * Test a string with no authority and a path with no forward slash at the start.
	 * This should produce a null authority with all other parts not null and not empty.
	 * A path isn't required to start with a forward slash.
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
	 * Test a string with no query nor fragment. This should produce a URI
	 * with only query and fragment being null, as they are not required for
	 * a URI.
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
	 * Test a string where the scheme is one character long. This should be
	 * allowed as scheme needs to be at least one character, so all parts
	 * of the URI should be non null.
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
	 * Test a string where the scheme is only a forward slash character. This should
	 * produce a null scheme and authority, as forward slash is an illegal character
	 * for schemes, but not for paths, meaning the start of the string should
	 * be the path.
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
	 * Test a string where the scheme is only a question mark character. THis should 
	 * produce a null scheme, authority and path, as question mark is an illegal character
	 * for scheme. Everything after the question mark that is not a hash character
	 * should be the query.
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

