const { createApp } = Vue;

createApp({
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      clientsInformation: [],
      respuestaJSON: "",
    };
  },
  created() {
    axios
      .get("/api/clients")
      .then((response) => {
        apiResp = response.data;
        this.clientsInformation = apiResp;
        this.respuestaJSON = response.data;
        console.log(apiResp);
      })
      .catch((error) => console.log(error));
  },
  methods: {
    addUser() {
      const clientsData = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
      };
      axios
        .post("/api/clients", clientsData)
        .then((response) => {
          alert("Usuario creado");
          console.log(response);
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");
