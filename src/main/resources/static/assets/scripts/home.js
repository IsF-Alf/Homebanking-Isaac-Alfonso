const { createApp } = Vue;

createApp({
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      clientsInformation: [],
      clientsData: [],
      respuestaJSON: "",
    };
  },
  created() {
    axios
      .get("/clients")
      .then((response) => {
        apiResp = response.data._embedded.clients;
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
        .post("/clients", clientsData)
        .then((response) => {
          alert("Usuario creado");
          console.log(response);
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");
