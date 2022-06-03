const { createApp } = Vue

createApp({
    data() {
        return {
            appointments: [],
            appointmentDescription: "",
            appointmentStartTime: null,
            appointmentEndTime: null
        }
    },
    created() {
        this.fetchAppointments();

        const nowMilliseconds = Date.now();

        this.appointmentStartTime = (new Date(nowMilliseconds)).toISOString();
        this.appointmentEndTime = (new Date(nowMilliseconds + 60 * 60 * 1000)).toISOString();
    },
    methods: {
        fetchAppointments() {
            fetch("/appointments")
                .then(response => response.json())
                .then(appointments => {
                    this.appointments = appointments;
                });
        },
        addAppointment(event) {
            event.preventDefault();

            const parsedStartTime = new Date(this.appointmentStartTime);
            const parsedEndTime = new Date(this.appointmentEndTime);

            if (!this.appointmentDescription) {
                throw "Empty description";
            } else if (isNaN(parsedStartTime)) {
                throw "Invalid start date";
            } else if (isNaN(parsedEndTime)) {
                throw "Invalid end date";
            }

            let appointmentData = {
                description: this.appointmentDescription,
                startTime: parsedStartTime.toISOString(),
                endTime: parsedEndTime.toISOString()
            }

            fetch("/appointment", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(appointmentData)
            });
        }
    }
}).mount("#appointments")