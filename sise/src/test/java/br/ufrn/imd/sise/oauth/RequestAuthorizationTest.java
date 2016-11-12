package br.ufrn.imd.sise.oauth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.ufrn.imd.sise.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RequestAuthorizationTest {
	

	
	@Test
	@Parameters(method = "getRequestsParamP")
	public void getResponsePositiveTest(String request){
		RequestAuthorization auth = new RequestAuthorization();
		String stringResponse = null;
		try {
			stringResponse = auth.getResponse(request);
		} catch (InvalidServiceRequestException | UnauthorizedServiceRequestException | RequestException e) {

		}
//		assertNotNull("Response for request is valid", stringResponse);

	}
	
	@Test
	@Parameters(method = "getRequestsParamN")
	public void getResponseNegativeTest(String request){
		RequestAuthorization auth = new RequestAuthorization();
		String stringResponse = null;
		try {
			stringResponse = auth.getResponse(request);
		} catch (InvalidServiceRequestException | UnauthorizedServiceRequestException | RequestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
//		assertNull("Response for request is invalid", stringResponse);
	}
	
	
	public Object[] getRequestsParamP() {
		return new Object[] {
				new Object[]{"https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario"}
		};
	}

	public Object[] getRequestsParamN() {
		return new Object[] {
				new Object[]{"https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuar"}
		};
	}
}
