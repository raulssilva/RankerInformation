package br.ufrn.imd.sise.engine.filter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ExtractorTest {

	@Test
	@Parameters(method = "getContentAndTrashChars")
	public void getTermsPositiveTest(String content, String trashListChar) {
		
		Extractor extractorForTrashChars = new Extractor();
		Extractor extractorForNoTrashChars = new Extractor();
		List<String> trashListTerms = new ArrayList<String>();
		Filter filterWithChars = new Filter(trashListChar, trashListTerms);
		Filter filterWithoutChars = new Filter("", trashListTerms);
		
		Set<String> setTermsWithoutTrashChars = extractorForTrashChars.extract(content, filterWithChars, true);
		Set<String> setTermsWithTrashChars = extractorForNoTrashChars.extract(content, filterWithoutChars, true);
		
		int flag = 0;
		for (String termWithoutTrashChars : setTermsWithoutTrashChars) {
			if(!setTermsWithTrashChars.contains(termWithoutTrashChars)){
				flag = 1;
			}
		}

		assertEquals("Set of terms is valid based on the list of trash chars", 1, flag);
	}

	public Object[] getContentAndTrashChars() {
		return new Object[] {
				new Object[]{"Hello World!", "@#$%!"}
		};
	}
}
