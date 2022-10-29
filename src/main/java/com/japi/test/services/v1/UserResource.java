package com.japi.test.services.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.japi.test.UserService;
import com.japi.test.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/v1/users")
@Api(value="/users", description="Manage Users")
public class UserResource {
	
	private static final Logger log = Logger.getLogger(UserResource.class.getName());
	
	@GET
	@Path("/xx")
	@Produces(MediaType.TEXT_PLAIN)
	public String xx() {
		return "Welcome to xx";
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Find All Users", notes="This service find all users.")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success:{users: []}"),
			@ApiResponse(code=400, message="Failed:{\"error\":\"error description\",\"status\":\"FAILED\"}")
	})
	public Response getUsers() {		
		
		try {
			List<User> users = UserService.getInstance().findUsers();
			UserHolder userHolder = new UserHolder();
			userHolder.setUsers(users);
			
			return Response.status(Response.Status.OK).entity(userHolder).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("UserResource :: getUsers - error found.");
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Could not found user\",\"status\":\"FAILED\"}").build();
	
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="create new user", notes="This API creates a new user if not exists "+"<p><u>Input Parameter</u><ul><li>User Object is Required</li></ul></p>")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Success:{user profile}"),
			@ApiResponse(code=400, message="Failed:{\"error\":\"error description\",\"status\":\"FAILED\"}")
	})
	public Response saveUser(@ApiParam(value="new user",required=true,defaultValue="\"{\"name\":\"Mojidul\"}\"",allowableValues="",allowMultiple=false) User user) {		
		
		try {
			String msg = UserService.getInstance().saveUser(user);
			Map<String, String> map = new HashMap<String, String>();
			map.put("Stats", msg);
			
			return Response.status(Response.Status.CREATED).entity(map).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("UserResource :: saveUser - error found.");
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Could not create user\",\"status\":\"FAILED\"}").build();
	
	}

}
