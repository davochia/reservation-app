package com.neagen.devtest.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class AppointmentController {
    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public static String APPOINTMENTS = "com.neagen.devtest.rest.Appointments";

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/appointments")
    @ApiOperation(value = "Get all appointments from the system.", notes = "Return appointments ")
    public List<Appointment> appointments(){
        List<Appointment> appointments = appointmentRepository.findAll(Sort.by(Sort.Direction.ASC, "endTime"));

        return appointments;
    }


    @PostMapping("/appointment")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an appointment instance.", notes = "Return appointment created")
    public Appointment appointment( @RequestBody Appointment appointment){
        if (appointment == null) throw new RuntimeException("Data is empty");

        return appointmentRepository.save(appointment);
    }

    @GetMapping("/appointments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation(value = "Get an appointment by Id.", notes = "Return appointment instance")
    public Appointment getAppointmentById(@ApiParam(value = "The ID of the existing appointment.", required = true)
                                               @PathVariable("id") Long id,  HttpServletRequest request) throws RuntimeException  {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);


        if (!optionalAppointment.isPresent()) throw new RuntimeException("Appointment with ID: " + id + " not found");

        Appointment appointment = optionalAppointment.get();

        return appointment;
    }


    @PutMapping("/appointments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Edit an appointment instance.", notes = "Return new edited appointment")
    public Appointment editAppointment(@ApiParam(value = "The ID of the existing appointment.", required = true)
                                  @PathVariable Long id, @RequestBody Appointment newAppointment) throws RuntimeException {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (!optionalAppointment.isPresent()) throw new RuntimeException("id: " + id + " not found");

        Appointment appointment = optionalAppointment.get();

        appointment.setName(newAppointment.getName());
        appointment.setDescription(newAppointment.getDescription());
        appointment.setEndTime(newAppointment.getEndTime());
        appointment.setStartTime(newAppointment.getStartTime());

        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/appointments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an appointment instance.", notes = "Return null")
    public void deleteAppointment(@ApiParam(value = "The ID of the existing appointment.", required = true)
                                      @PathVariable Long id) throws RuntimeException{
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (!optionalAppointment.isPresent()) throw new RuntimeException("id: " + id + " not found");

       appointmentRepository.deleteById(id);
    }

    //  @GetMapping("/appointmentsByName/{name}")
//  @ApiOperation(value = "Get all appointments from the system search by name.", notes = "Return appointments ")
//  public List<Appointment> appointmentsByName (@PathVariable String name){
//        List<Appointment> appointmentList = appointments();
//
//        List<Appointment> appointments =  new ArrayList<>();
//                appointmentList.forEach(appointment -> {
//                    if(appointment.getName().equalsIgnoreCase(name)){
//                        appointments.add(appointment);
//                    }
//                });
//
//        return appointments;
//  }

    private List<Appointment> initializeAppointments(){
        List<Appointment> appointments = new ArrayList<>();
        Random rand = new Random();
        int max = 14;
        for (int i=1; i<=max; i++){
            int offset = rand.nextInt(max * 24);
            appointments.add(new Appointment((long) i,"Mark "+i,
                    Instant.now().plus(offset, ChronoUnit.HOURS),
                    Instant.now().plus(offset+1, ChronoUnit.HOURS), "Appointment #"+i));
        }
        return appointments;
    }
}
