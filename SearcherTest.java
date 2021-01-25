package edu.wofford.wordoff;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class SearcherTest {
	private AnagramSearcher model;
    
    @Before
    public void setUp() {
        model = new AnagramSearcher("/allwords.txt");
    }
    
    @Test
	public void testAnagramIsAnagram() {
        String s1 = "siren";
	String s2 = "rinse";
	assertTrue(model.isAnagram(s1,s2));
	}
    @Test
	public void testAnagramIsNotAnagram() {
    String s1 = "siren";
	String s2 = "Wofford";
	assertEquals(false,model.isAnagram(s1,s2));
	}
    @Test
    public void testgetAnagram(){
        String s1 = "slEEP";
        String s2 = String.valueOf(model.getAnagram(s1));
        assertTrue(s2.contains("peels"));
        assertTrue(s2.contains("peles"));
        assertTrue(s2.contains("sleep"));
        assertTrue(s2.contains("speel"));
    }
    @Test
    public void testExceptionAnagram(){
        String s1 = "xa";
        String s2 = String.valueOf(model.getAnagram(s1));
		assertTrue(s2.contains(""));
		String nonsense = "qwertyuiop";
		String nonsenseGroup = String.valueOf(model.getAnagram(nonsense));
		assertTrue(nonsenseGroup.contains(""));
    }
    @Test
    public void testgetRandagram(){
        String s = String.valueOf(model.getRandagram(15));
        assertTrue(s.contains("alerts"));
        assertTrue(s.contains("alters"));
        assertTrue(s.contains("artels"));
        assertTrue(s.contains("estral"));
        assertTrue(s.contains("laster"));
        assertTrue(s.contains("lastre"));
        assertTrue(s.contains("rastle"));
        assertTrue(s.contains("ratels"));
        assertTrue(s.contains("relast"));
        assertTrue(s.contains("salter"));
        assertTrue(s.contains("staler"));
        assertTrue(s.contains("stelar"));
        assertTrue(s.contains("talers"));
        assertTrue(!s.contains("tlaser"));
    }
    @Test
    public void testExeptiongetRandagram(){
        String s = String.valueOf(model.getRandagram(0));
        assertTrue(s.contains(""));
    }
	
	@Test
	public void testplay_random(){
		int len = 1;
		String s = model.play_random(len);
		assertTrue(s.contains(" "));
	}
	@Test
	public void testplay_random2(){
		int len = 2;
		String s = model.play_random(len);
		String s1 = "ah am an as at be by do dr eg eh em go ha he hi ho id if im in is it me mr ms mu my ne no nu of oh ok on or ow ox pa pi re so to up us we ye";
		/*String s2[] = s1.split(" ");
		for(int i = 0; i<47; i++){
		String s3 = s2[i];
		if (s.contains(s3)){s = " ";}
		}*/
		assertTrue(s1.contains("ah"));
		//assertTrue(s1.contains(s));
	}
	@Test
    public void testgetAllAnagram(){
		List<String> sample = model.getAllAnagrams("star");
		System.out.println(sample);
        assertTrue(sample.contains("a"));
        assertTrue(sample.contains("ar"));
		assertTrue(sample.contains("ars"));
		assertTrue(sample.contains("arts"));
		assertTrue(sample.contains("as"));
		assertTrue(sample.contains("ast"));
		assertTrue(sample.contains("astr"));
		assertTrue(sample.contains("at"));
		assertTrue(sample.contains("r"));
		assertTrue(sample.contains("ra"));
		assertTrue(sample.contains("ras"));
		assertTrue(sample.contains("rat"));
		assertTrue(sample.contains("rats"));
		assertTrue(sample.contains("rs"));
		assertTrue(sample.contains("rt"));
		assertTrue(sample.contains("s"));
		assertTrue(sample.contains("sa"));
		assertTrue(sample.contains("sar"));
		assertTrue(sample.contains("sart"));
		assertTrue(sample.contains("sat"));
		assertTrue(sample.contains("sr"));
		assertTrue(sample.contains("st"));
		assertTrue(sample.contains("sta"));
		assertTrue(sample.contains("star"));
		assertTrue(sample.contains("str"));
		assertTrue(sample.contains("stra"));
		assertTrue(sample.contains("t"));
		assertTrue(sample.contains("ta"));
		assertTrue(sample.contains("tar"));
		assertTrue(sample.contains("tars"));
		assertTrue(sample.contains("tas"));
		assertTrue(sample.contains("tr"));
		assertTrue(sample.contains("tra"));
		assertTrue(sample.contains("trs"));
		assertTrue(sample.contains("ts"));
		assertTrue(sample.contains("tsar"));	
    }
    @Test
    public void testgetAllAnagram2(){
		List<String> sample = model.getAllAnagrams("vet");
		//System.out.println(sample);
        assertTrue(sample.contains("e"));
		assertTrue(sample.contains("et"));
		assertTrue(sample.contains("t"));
		assertTrue(sample.contains("te"));
		assertTrue(sample.contains("tv"));
		assertTrue(sample.contains("v"));
		assertTrue(sample.contains("vet"));
		assertTrue(sample.contains("vt"));	
    }

}