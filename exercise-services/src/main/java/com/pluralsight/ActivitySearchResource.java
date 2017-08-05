package com.pluralsight;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("search/activities")
public class ActivitySearchResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions,
			@QueryParam("durationFrom") Integer durationFrom, @QueryParam("durationTo") Integer durationTo) {

		System.out.println("GET-search:: descriptions: " + descriptions);
		System.out.println("GET-search:: durationFrom: " + durationFrom);
		System.out.println("GET-search:: durationTo: " + durationTo);

		List<Activity> activities = activityRepository.searchByParams(descriptions, durationFrom, durationTo);

		if (activities == null || activities.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
		}).build();
	}
}
