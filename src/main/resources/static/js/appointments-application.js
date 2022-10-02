const { createApp } = Vue;
// import Router from "vue-router";

createApp({
  data() {
    return {
      appointments: [],
      appointmentName: "",
      appointmentDescription: "",
      appointmentStartTime: null,
      appointmentEndTime: null,
      toggleFormButton: false,
      nowDateTime: new Date(Date.now()).toISOString(),
    };
  },
  mounted() {
    const params = new Proxy(new URLSearchParams(window.location.search), {
      get: (searchParams, prop) => searchParams.get(prop),
    });
    this.appointmentName = params.name;
  },
  created() {
    this.fetchAppointments();

    const nowMilliseconds = Date.now();

    this.appointmentStartTime = new Date(nowMilliseconds).toISOString();
    this.appointmentEndTime = new Date(
      nowMilliseconds + 60 * 60 * 1000
    ).toISOString();
  },

  methods: {
    /**
     * Fetch appointment from system
     */
    fetchAppointments() {
      fetch(`/appointments`)
        .then((response) => response.json())
        .then((appointments) => {
          this.appointments = appointments.filter(
            (appointment) =>
              appointment.name.toLowerCase() ===
              this.appointmentName.toLowerCase()
          );
        });
    },

    /**
     * Add new appointment to system
     * @param {*} event Data from form
     */
    async addAppointment(event) {
      event.preventDefault();

      const parsedStartTime = new Date(this.appointmentStartTime);
      const parsedEndTime = new Date(this.appointmentEndTime);

      console.log("This start: ", this.appointmentStartTime);
      console.log("Parsed: ", parsedStartTime);

      if (!this.appointmentDescription) {
        throw "Empty description";
      } else if (isNaN(parsedStartTime)) {
        throw "Invalid start date";
      } else if (isNaN(parsedEndTime)) {
        throw "Invalid end date";
      } else if (parsedEndTime < parsedStartTime) {
        throw "Invalid date range";
      }

      let appointmentData = {
        name: this.appointmentName,
        description: this.appointmentDescription,
        startTime: parsedStartTime.toISOString(),
        endTime: parsedEndTime.toISOString(),
      };

      console.log("This start new: ", appointmentData.startTime);

      const res = await fetch("/appointment", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(appointmentData),
      });

      this.reset();
      const data = await res.json();
      this.appointments = [...this.appointments, data].sort(
        (a, b) => a.endTime < b.endTime
      );
      // this.fetchAppointments();
    },

    /**
     *
     * @param {*} appoint appointment to edit pass to form
     */
    editAppointment(appoint) {
      this.appointmentId = appoint.id;
      this.appointmentDescription = appoint.description;
      this.appointmentStartTime = appoint.startTime;
      this.appointmentEndTime = appoint.endTime;

      this.toggleFormButton = true;
    },

    /**
     * Save appointment to server
     */
    async onSave() {
      const parsedStartTime = new Date(this.appointmentStartTime);
      const parsedEndTime = new Date(this.appointmentEndTime);

      if (!this.appointmentDescription) {
        throw "Empty description";
      } else if (isNaN(parsedStartTime)) {
        throw "Invalid start date";
      } else if (isNaN(parsedEndTime)) {
        throw "Invalid end date";
      } else if (parsedEndTime < parsedStartTime) {
        throw "Invalid date range";
      }

      let appointmentData = {
        description: this.appointmentDescription,
        startTime: parsedStartTime.toISOString(),
        endTime: parsedEndTime.toISOString(),
      };

      const res = await fetch(`/appointments/${this.appointmentId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(appointmentData),
      });
      this.reset();
      const data = await res.json();
      this.appointments = [...this.appointments, data].sort(
        (a, b) => a.endTime < b.endTime
      );
    },

    /**
     *
     * @param {*} appointment appointment to delete from system
     */
    deleteAppointment(appointment) {
      if (confirm("Are you sure?")) {
        fetch(`/appointments/${appointment.id}`, { method: "DELETE" })
          .then(async (response) => {
            const isJson = response.headers
              .get("content-type")
              .includes("application/json");
            const data = isJson && (await response.json());

            if (!response.ok) {
              const error = (data && data.message) || response.status;
              return Promise.reject(error);
            }
            this.status = "Delete successful";
          })
          .catch((error) => {
            this.errorMessage = error;
            console.error("There was an error!", error);
          });

        this.appointments = this.appointments.filter(
          (appoint) => appoint != appointment
        );
      }
    },

    /**
     *
     * @param {*} dateString Date ISO string to format
     * @returns formated date
     */
    formatDate(dateString) {
      const date = new Date(dateString);

      return new Intl.DateTimeFormat("default", {
        dateStyle: "full",
        timeStyle: "long",
      }).format(date);
    },

    /**
     * reset data from form
     */
    reset() {
      this.appointmentId = null;
      this.appointmentName = "";
      this.appointmentDescription = "";
      this.appointmentStartTime = null;
      this.appointmentEndTime = null;
    },

    /**
     * Toggle form
     */
    toggleForm() {
      this.toggleFormButton = !this.toggleFormButton;
    },
  },
}).mount("#appointments");
