package org.upgrad.upstac.testrequests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.web.server.ResponseStatusException;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.testrequests.consultation.ConsultationController;
import org.upgrad.upstac.testrequests.consultation.CreateConsultationRequest;
import org.upgrad.upstac.testrequests.consultation.DoctorSuggestion;
import org.upgrad.upstac.testrequests.lab.CreateLabResult;
import org.upgrad.upstac.testrequests.lab.TestStatus;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.testrequests.RequestStatus;
import org.upgrad.upstac.testrequests.TestRequestQueryService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;


@SpringBootTest
@Slf4j
class ConsultationControllerTest {


    @Autowired
    ConsultationController consultationController;


    @Autowired
    TestRequestQueryService testRequestQueryService;


    @Test
    @WithUserDetails(value = "doctor")
    public void calling_assignForConsultation_with_valid_test_request_id_should_update_the_request_status(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.LAB_TEST_COMPLETED);

        //Implement this method

        //Create another object of the TestRequest method and explicitly assign this object for Consultation using assignForConsultation() method
        // from consultationController class. Pass the request id of testRequest object.
        
        TestRequest testRequest1 = getTestRequestByStatus(RequestStatus.LAB_TEST_COMPLETED);
        consultationController.assignForConsultation(testRequest1.getRequestId());
        
        //Use assertThat() methods to perform the following two comparisons
        //  1. the request ids of both the objects created should be same
        //  2. the status of the second object should be equal to 'DIAGNOSIS_IN_PROCESS'
        
       
        	long actual = testRequest.getRequestId();
        	long expected = testRequest1.getRequestId();
        	Assertions.assertEquals(actual,expected);
        	Assertions.assertEquals("DIAGONIS_IN_PROCESS", testRequest1.getStatus());
        
        // make use of assertNotNull() method to make sure that the consultation value of second object is not null
        // use getConsultation() method to get the lab result
        assertNotNull(testRequest1.getConsultation());

    }

    public TestRequest getTestRequestByStatus(RequestStatus status) {
        return testRequestQueryService.findBy(status).stream().findFirst().get();
    }

    
    @Test
    @WithUserDetails(value = "doctor")
    public void calling_assignForConsultation_with_valid_test_request_id_should_throw_exception(){

        Long InvalidRequestId= -34L;

        //Implement this method


        // Create an object of ResponseStatusException . Use assertThrows() method and pass assignForConsultation() method
        // of consultationController with InvalidRequestId as Id
        ResponseStatusException resp = null;
        
        
        Assertions.assertThrows(AppException.class, ()-> {consultationController.assignForConsultation(InvalidRequestId);});

        //Use assertThat() method to perform the following comparison
        //  the exception message should be contain the string "Invalid ID"
        Assertions.assertEquals(-34L,consultationController.assignForConsultation(InvalidRequestId),"Invalid Id");
        	
    }

   
    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_valid_test_request_id_should_update_the_request_status_and_update_consultation_details(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        //Implement this method
        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. 
        //Pass the above created object as the parameter
        
        CreateConsultationRequest creq=null;
        ConsultationControllerTest.getCreateConsultationRequest(creq);
        
        //Create another object of the TestRequest method and explicitly update the status of this object to be 'COMPLETED'. 
        TestRequest testRequest1 = getTestRequestByStatus(RequestStatus.COMPLETED);
        
        // Make use of updateConsultation() method from consultationController class
        // (Pass the previously created two objects as parameters)
        // (for the object of TestRequest class, pass its ID using getRequestId())
        consultationController.updateConsultation(testRequest1.getRequestId(), creq);
        
        //Use assertThat() methods to perform the following three comparisons
        //  1. the request ids of both the objects created should be same
        //  2. the status of the second object should be equal to 'COMPLETED'
        // 3. the suggestion of both the objects created should be same. Make use of getSuggestion() method to get the results.
        
        

    }


    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_invalid_test_request_id_should_throw_exception(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        //Implement this method

        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. Pass the above created object as the parameter
        CreateConsultationRequest creq=null;
        ConsultationControllerTest.getCreateConsultationRequest(creq);
        // Create an object of ResponseStatusException . Use assertThrows() method and pass updateConsultation() method
        ResponseStatusException res=null;
        Exception ex=assertThrows(AppException.class,()->{consultationController.updateConsultation(-34L,creq);});
        
 
        // of consultationController with a negative long value as Id and the above created object as second parameter
        //Refer to the TestRequestControllerTest to check how to use assertThrows() method

        assertThat("Invlid Id", is(ex.getMessage()));
        //Use assertThat() method to perform the following comparison
        //  the exception message should be contain the string "Invalid ID"

    }

    
    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_invalid_empty_status_should_throw_exception(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        //Implement this method
        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. Pass the above created object as the parameter
        // Set the suggestion of the above created object to null.
        CreateConsultationRequest creq = null;
        creq.setComments(null);
        creq.setSuggestion(null);
        ConsultationControllerTest.getCreateConsultationRequest(creq);
        
        // Create an object of ResponseStatusException . Use assertThrows() method and pass updateConsultation() method
        // of consultationController with request Id of the testRequest object and the above created object as second parameter
        //Refer to the TestRequestControllerTest to check how to use assertThrows() method
        ResponseStatusException responseStatusException=null;
        Exception ex=assertThrows(AppException.class,()->{consultationController.updateConsultation(testRequest.getRequestId(), creq);});
        
    }

    
    
    public static CreateConsultationRequest getCreateConsultationRequest(CreateConsultationRequest creq) {

        //Create an object of CreateLabResult and set all the values
        // if the lab result test status is Positive, set the doctor suggestion as "HOME_QUARANTINE" and comments accordingly
        // else if the lab result status is Negative, set the doctor suggestion as "NO_ISSUES" and comments as "Ok"
        // Return the object

    	CreateLabResult clab=null;
    	
    	clab.setBloodPressure(clab.getBloodPressure());
    	clab.setComments(clab.getComments());
    	clab.setHeartBeat(clab.getComments());
    	clab.setOxygenLevel(clab.getOxygenLevel());
    	clab.setResult(clab.getResult());
    	clab.setTemperature(clab.getTemperature());
    	
    	if(clab.getResult().equals(TestStatus.POSITIVE)) {
    		creq.setSuggestion(DoctorSuggestion.HOME_QUARANTINE);
    		creq.setComments("HOME_QUARANTINE");
    		clab.setComments("HOME_QUARANTINE");
    	}
    	else {
    		creq.setSuggestion(DoctorSuggestion.NO_ISSUES);
    		creq.setComments("Ok");
    		clab.setComments("Ok");
    	}
    	return creq;
    }

}