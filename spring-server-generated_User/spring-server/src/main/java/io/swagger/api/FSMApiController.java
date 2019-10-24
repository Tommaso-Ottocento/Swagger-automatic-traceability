package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T23:08:41.269+02:00")

@Controller
public class FSMApiController implements FSMApi {

    private static final Logger log = LoggerFactory.getLogger(FSMApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FSMApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<User>> compute(@ApiParam(value = "give in input PI or PC or -", required = true) @PathVariable("loc") String loc, @ApiParam(value = "Identification code from 0 to 4", required = true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            List<User> users = new ArrayList<>();
            users.add(new User("Davide", "Quaglia"));
            users.add(new User("Dawit", "Abate"));
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
