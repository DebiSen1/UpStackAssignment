package org.upgrad.upstac.testrequests.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.upgrad.upstac.testrequests.RequestStatus;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.users.User;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class TestRequestFlow {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @ManyToOne
    @JsonIgnore
    private TestRequest request;

    private RequestStatus fromStatus ;
    private RequestStatus toStatus ;

    @ManyToOne
    private User changedBy;

    private LocalDate happenedOn=LocalDate.now();

	public void setChangedBy(User changedBy2) {
		// TODO Auto-generated method stub
		this.changedBy=changedBy2;
	}

	public void setRequest(TestRequest testRequest) {
		// TODO Auto-generated method stub
		this.request=testRequest;
	}

	public void setFromStatus(RequestStatus from) {
		// TODO Auto-generated method stub
		this.fromStatus=from;
	}

	public void setToStatus(RequestStatus to) {
		// TODO Auto-generated method stub
		this.toStatus=to;
	}
}
