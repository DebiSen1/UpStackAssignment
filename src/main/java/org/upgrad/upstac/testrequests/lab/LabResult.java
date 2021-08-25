package org.upgrad.upstac.testrequests.lab;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.users.User;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class LabResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resultId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private TestRequest request;

    private String bloodPressure;
    private String heartBeat;
    private String temperature;
    private String oxygenLevel;
    private String comments;
    private TestStatus result;
    private LocalDate updatedOn;

    @ManyToOne
    private User tester;

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public TestRequest getRequest() {
		return request;
	}

	public void setRequest(TestRequest request) {
		this.request = request;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(Object object) {
		this.bloodPressure = (String) object;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(Object object) {
		this.heartBeat = (String) object;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(Object object) {
		this.temperature = (String) object;
	}

	public String getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(Object object) {
		this.oxygenLevel = (String) object;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(Object object) {
		this.comments = (String) object;
	}

	public TestStatus getResult() {
		return result;
	}

	public void setResult(Object object) {
		this.result = (TestStatus) object;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getTester() {
		return tester;
	}

	public void setTester(User tester) {
		this.tester = tester;
	} 
}
