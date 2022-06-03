package com.neagen.devtest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class AppointmentController {

    public static String APPOINTMENTS = "com.neagen.devtest.rest.Appointments";

    @GetMapping("/appointments")
    public List<Appointment> appointments(HttpServletRequest request){
        List<Appointment> appointments =  (List<Appointment>) request.getSession().getAttribute(APPOINTMENTS);
        if (appointments == null) {
            // no appointments!? Have to create some now ones
            appointments = initializeAppointments();
            request.getSession().setAttribute(APPOINTMENTS, appointments);
        }
        return appointments;
    }

    private List<Appointment> initializeAppointments(){
        List<Appointment> appointments = new ArrayList<>();
        Random rand = new Random();
        int max = 14;
        for (int i=1; i<=max; i++){
            int offset = rand.nextInt(max * 24);
            appointments.add(new Appointment("Appointment #"+i,
                    Instant.now().plus(offset, ChronoUnit.HOURS),
                    Instant.now().plus(offset+1, ChronoUnit.HOURS)));
        }
        return appointments;
    }
}
