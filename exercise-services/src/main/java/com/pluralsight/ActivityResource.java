package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam("activityId") String activityId) {
		System.out.println("DELETE: " + activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
	
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Activity activity) {
		System.out.println("PUT: " + activity.getActivityId());
		
		activityRepository.update(activity);
		
		return Response.ok().entity(activity).build();
	}
	
	@POST
	@Path("activity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityBinding(Activity activity) {
		System.out.println("POST binding: " + activity);
		activity.setDuration(activity.getDuration() + 10);
		activityRepository.create(activity);
		return activity;
	}

	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		String description = formParams.getFirst("description");
		Integer duration = Integer.parseInt(formParams.getFirst("duration"));

		System.out.println("POST x-form: description:" + description);
		System.out.println("POST x-form: duration:" + duration);
		System.out.println("");

		Activity activity = new Activity(description, duration);

		activityRepository.create(activity);
		return activity;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Activity> getAllActivities() {
		System.out.println("GET");
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}")
	public Response getActivity(@PathParam("activityId") String activityId) {
		System.out.println("GET: " + activityId);
		
		if (activityId.equals("null") || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Activity activity = activityRepository.findActivity(activityId);

		if (activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		System.out.println(String.format("Getting activity with id [%s]", activityId));

		return Response.ok().entity(activity).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}/user")
	public User getActivityUser(@PathParam("activityId") String activityId) {
		Activity activity = activityRepository.findActivity(activityId);
		User user = activity.getUser();
		return user;
	}
}
